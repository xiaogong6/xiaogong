package com.xiaogong.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-09
 */
public class SemaphoreThreadExecute {

    public static void main(String[] args) throws InterruptedException {
        // 创建Semaphore对象，用来做线程通信
        Semaphore semaphore = new Semaphore(1);

        // 等待线程T1执行完
        semaphore.acquire();

        // 创建并启动线程T1
        Thread t1 = new Thread(new MyThread3(semaphore), "T1");
        t1.start();

        // 等待线程T2执行完
        semaphore.acquire();

        // 创建并启动线程T2
        Thread t2 = new Thread(new MyThread3(semaphore), "T2");
        t2.start();

        // 等待线程T3执行完
        semaphore.acquire();

        // 创建并启动线程T3
        Thread t3 = new Thread(new MyThread3(semaphore), "T3");
        t3.start();
    }
}

class MyThread3 implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(MyThread3.class);

    private final Semaphore semaphore;

    public MyThread3(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is Running.");
        } catch (InterruptedException e) {
            logger.error("Thread execution error:{}", e.getMessage());
        } finally {
            // 释放许可证，表示完成一个线程
            semaphore.release();
        }
    }
}