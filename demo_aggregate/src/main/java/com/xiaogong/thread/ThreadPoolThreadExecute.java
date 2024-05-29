package com.xiaogong.thread;

import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-10
 */
public class ThreadPoolThreadExecute {
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 创建并启动线程T1
        executor.submit(new MyThread4("T1"));

        // 创建并启动线程T2
        executor.submit(new MyThread4("T2"));

        // 创建并启动线程T3
        executor.submit(new MyThread4("T3"));

        // 关闭线程池
        executor.shutdown();
    }
}

class MyThread4 implements Runnable {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(MyThread4.class);
    private final String name;

    public MyThread4(String name) {
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
