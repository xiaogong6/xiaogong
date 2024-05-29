package com.xiaogong;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.xiaogong.utils.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@SpringBootTest
class DemoRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private DefaultRedisScript<Long> generateSerialNumber;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void printCode() {
        System.out.println(generateOrderCode(66624L));
    }

    @Test
    void contextLoads() {
        System.out.println(generateOrderCode(66624L));
    }

    public String generateOrderCode(Long tenantId) {
        List<String> keys = new ArrayList<>();
        keys.add(MessageFormat.format("order:generatenumber:{0}", tenantId));
        Object result = redisTemplate.execute(generateSerialNumber, keys, String.valueOf(1000000));
        return generateOrderCodeV2(result);
    }

    /**
     * 单号生产规则；
     *
     * @param generateNumber
     * @return
     */
    private String generateOrderCodeV2(Object generateNumber) {
        // D YYMMDD 流水号（6位） 随机数（4位）
        return getRandomCodeV2((Long) generateNumber);
    }

    /**
     * 雅戈尔版本 单号生产规则；
     * 前缀+6位日期+流水号+随机数+后缀
     */
    private static String getRandomCodeV2(long generateNumber) {

        String dateStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        dateStr = sdf.format(new Date());
        // 随机数生成
        String random = new Random().nextInt(1000) + "";
        int times = Integer.toString(1000).length() - random.length();
        for (int i = 0; i < times; i++) {
            random = "0" + random;
        }
        // 流水号格式处理
        StringBuilder generateNumberStr = new StringBuilder(String.valueOf(generateNumber));
        int complementLength = 6 - generateNumberStr.length();
        for (int i = 0; i < complementLength; i++) {
            generateNumberStr.insert(0, "0");
        }
        return "D" + dateStr + generateNumberStr + random;

    }

    @Test
    public void test11(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(this::testRedisson);


    }


    @Test
    public void testRedisson(){
        //防止任务并发
        String redisKey = "xiongke_redisson";
        RLock lock = redissonClient.getLock(redisKey);
        try {
            boolean tryLock = lock.tryLock();
            if (!tryLock) {
                System.out.println("任务正在执行中，请稍后再试");
                return;
            }

            List<String> list = Arrays.asList("1", "2", "3", "5", "6", "7", "8", "9", "0");

            if (ObjectUtils.isEmpty(list)) {
                return;
            }

            List<Future> jobResults = new ArrayList<>();

            //拆分集合
            List<List<String>> partitionList = ListUtil.splitAvg(list, 2);

            //处理逻辑
            for (List<String> clearIntegralList : partitionList) {
                Future jobResult = ThreadPoolUtils.submit(() -> {
                    for (String outIntegral : clearIntegralList) {
                        try {
                            System.out.println("定时清理过期积分任务执行中" + outIntegral);
                        } catch (Exception e) {
                            System.out.println("定时清理过期积分任务异常");
                            String errorMsg = StrUtil.subPre(e.getMessage(), 2000);
                            System.out.println("定时清理过期积分任务异常" + errorMsg);
                        }
                    }
                });
                jobResults.add(jobResult);
            }

            // 阻塞等待
            for (Future future : jobResults) {
                future.get();
            }

            //清空数据
            list.clear();
            partitionList.clear();
        } catch (Exception e) {
            System.out.println("定时清理过期积分任务异常"+e);
        } finally {
            lock.unlock();
            System.out.println("定时清理过期积分任务执行完毕");
        }
    }

}
