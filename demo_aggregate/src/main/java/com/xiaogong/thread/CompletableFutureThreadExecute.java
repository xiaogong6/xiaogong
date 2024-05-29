package com.xiaogong.thread;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-10
 */
public class CompletableFutureThreadExecute {
    public static void main(String[] args) {
        // 创建CompletableFuture对象
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(new MyThread5("T1"));

        // 等待线程T1完成
        future1.join();

        // 创建CompletableFuture对象
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(new MyThread5("T2"));

        // 等待线程T2完成
        future2.join();

        // 创建CompletableFuture对象
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(new MyThread5("T3"));

        // 等待线程T3完成
        future3.join();
    }

    @Test
    public void test02(){
        // 创建CompletableFuture对象
        CompletableFuture<Void> future = CompletableFuture.runAsync(new MyThread5("T1")).thenRun(new MyThread5("T2")).thenRun(new MyThread5("T3"));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

class MyThread5 implements Runnable {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(MyThread5.class);
    private final String name;

    public MyThread5(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            System.out.println(name + " is Running.");
        } catch (InterruptedException e) {
            logger.error("Thread execution interrupted: " + e.getMessage());
        }
    }
}
