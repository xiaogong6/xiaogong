package com.xiaogong;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
class DemoRabbitmqApplicationTests {

    @Autowired
    TransactionTemplate transactionTemplate;

    private final Logger logger = LoggerFactory.getLogger(DemoRabbitmqApplicationTests.class);

    @Test
    void contextLoads() {

    }

    @Test
    public void orderTest(Object object) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                createOrder(object);
                createMessage(object);
            }
        });
        sendMessage(object);
        updateStatus(object);
    }

    @Test
    public void orderTest2(Object object) {
        // 以上执行如果未抛异常，则成功，返回true
        // 表示事务执行成功
        // 如果发生异常，则标记事务为回滚
        // 表示事务执行失败
        boolean transactionSuccess = Boolean.TRUE.equals(transactionTemplate.execute(status -> {
            try {
                createOrder(object);
                createMessage(object);
                // 以上执行如果未抛异常，则成功，返回true
                return true; // 表示事务执行成功
            } catch (Exception e) {
                // 如果发生异常，则标记事务为回滚
                status.setRollbackOnly();
                return false; // 表示事务执行失败
            }
        }));

        if (transactionSuccess) {
            // 事务执行成功，可以执行 mqService.send(orderDTO)
            sendMessage(object);
            updateStatus(object);
        } else {
            // 事务执行失败的处理逻辑
            // 可以抛出异常或记录日志等
            logger.error("Transaction failed");
        }
    }

    private void createOrder(Object object) {
        logger.info("create order:{}", object);
    }

    private void createMessage(Object object) {
        logger.info("create message:{}", object);
    }

    private void sendMessage(Object object) {
        logger.info("send message:{}", object);
    }

    private void updateStatus(Object object) {
        logger.info("update status:{}", object);
    }
}
