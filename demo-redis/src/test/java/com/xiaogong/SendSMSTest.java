package com.xiaogong;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaogong.service.mapper.SendSMSMapper;
import com.xiaogong.service.modle.SendSMS;
import com.xiaogong.utils.ALiYunSMS;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-05-28
 */
@SpringBootTest
@Service
public class SendSMSTest {

    private static final Logger log = LogManager.getLogger(SendSMSTest.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private SendSMSMapper sendSMSMapper;

    @SneakyThrows
    @Test
    public void sendSmsTest() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(() -> {
            this.sendSmsVerificationCode("18230615417", "2");
        });
    }


    public void sendSmsVerificationCode(String mobile, String type) {
        String smsSent = redisTemplate.opsForValue().get(getLimitKey(mobile, type));
        if (StrUtil.isNotBlank(smsSent)) {
            throw new RuntimeException("请勿频繁发送验证码");
        }

        // 记录手机号发送过验证码，记录1分钟
        redisTemplate.opsForValue().set(getLimitKey(mobile, type), "1", 60, TimeUnit.SECONDS);
        // 把验证码存在Redis中
        String key = type + mobile;
        // 使用hutool的工具生成四位随机验证码
        String code = RandomUtil.randomStringUpper(4);
        redisTemplate.opsForValue().set(key, code, 60 * 5, TimeUnit.SECONDS);

        // 构建一条发送记录
        SmsSendRecord smsSendRecord = new SmsSendRecord(mobile, type, code);
        SendSMS sendSMS = CommonConvert.INSTANCE.converToSendSMS(mobile, type, code);
        // 落库保存
        // this.save(smsSendRecord);

        sendSMSMapper.insert(sendSMS);

        // 发送短信验证码
        // this.sendSms(smsSendRecord);
        this.sendSMS(sendSMS);
    }

    private void save(SmsSendRecord smsSendRecord) {
        System.out.println("保存发送记录到数据库: " + JSONUtil.toJsonStr(smsSendRecord));
        // smsSendRecord.setId(RandomUtil.randomLong(10000));
    }

    private void sendSMS(SendSMS sms) {
        RLock lock = redisson.getLock("sms:" + sms.getId());
        try {
            // 1.加分布式锁
            if (lock.tryLock(10, 10, TimeUnit.SECONDS)) {
                // 2.判断是否为待发送状态
                if (sms.getStatus()!=1){
                    return;
                }
                // 3.短信发送逻辑 更新状态
                send(sms);
            }
        } catch (Exception e) {
            log.error("短信发送失败: {}", e.getMessage());
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    private void send(SendSMS sms) {
        Boolean result = ALiYunSMS.sendSMSByALiYun(sms.getCode(), sms.getPhone());
        if (result) {
            System.out.println("短信发送成功：--->" + sms.getCode());
            sms.setStatus(2);
            sendSMSMapper.updateById(sms);
        } else {
            log.error("短信发送失败: {}", sms.getPhone());
        }
    }

    private String getLimitKey(String mobile, String type) {
        return "xiaogong:sms:limit:" + mobile + ":" + type;
    }


    public Boolean verifySmsCode(SmsSendRecord param) {
        // 从Redis中取出验证码
        String verificationCode = redisTemplate.opsForValue().get(param.getType() + param.getMobile());
        if (verificationCode == null) {
            return false;
        }
        if (param.getVerificationCode().equals(verificationCode)) {
            redisTemplate.delete(param.getType() + param.getMobile());
            return true;
        }

        return false;
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void jobSend() {
        // job任务扫描待发送的短信 进行重试
        List<SendSMS> list = sendSMSMapper.selectList(Wrappers.<SendSMS>lambdaQuery()
                .eq(SendSMS::getStatus, 1)
        );
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        list.forEach(l -> executorService.submit(() -> this.sendSMS(l)));
    }
}


