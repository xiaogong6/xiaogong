package com.xiaogong;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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

    @SneakyThrows
    @Test
    public void sendSmsTest() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            threadPool.submit(() -> {
                this.sendSmsVerificationCode("18230615417", "2");
            });
        }
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }


    public void sendSmsVerificationCode(String mobile, String type) {
        String smsSended = redisTemplate.opsForValue().get(getLimitKey(mobile, type));
        if (StrUtil.isNotBlank(smsSended)) {
            throw new RuntimeException("请勿频繁发送验证码");
        }

        // 记录手机号发送过验证码，记录1分钟
        redisTemplate.opsForValue().set(getLimitKey(mobile, type), "1", 60, TimeUnit.SECONDS);
        // 使用hutool的工具生成四位随机验证码
        String verificationCode = RandomUtil.randomStringUpper(4);
        // 把验证码存在Redis中
        redisTemplate.opsForValue().set(type + mobile, verificationCode, 60 * 5, TimeUnit.SECONDS);

        // 构建一条发送记录
        SmsSendRecord smsSendRecord = new SmsSendRecord(mobile, type, verificationCode);
        // 落库保存
        this.save(smsSendRecord);

        // 发送短信验证码
        this.sendSms(smsSendRecord);
    }

    private void save(SmsSendRecord smsSendRecord) {
        System.out.println("保存发送记录到数据库: " + JSONUtil.toJsonStr(smsSendRecord));
        // smsSendRecord.setId(RandomUtil.randomLong(10000));
    }

    private void sendSms(SmsSendRecord smsSendRecord) {
        Long id = smsSendRecord.getId();
        RLock lock = redisson.getLock("sendSms" + id);
        try {
            if (lock.tryLock(10, 10, TimeUnit.SECONDS)) {
                // 短信发送逻辑
                System.out.println("短信发送成功：--->" + smsSendRecord.getVerificationCode());
            }
        } catch (Exception e) {
            log.error("短信发送失败: {}", e.getMessage());
        } finally {
            // 解锁
            lock.unlock();
        }
    }

    private String getLimitKey(String mobile, String type) {
        return "xiaogong:sms:limit:" + mobile + ":" + type;
    }

}

