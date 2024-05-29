package com.xiaogong.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-09
 */
public class CyclicBarrierThreadExecute {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        // 创建CyclicBarrier对象，用来做线程通信
        CyclicBarrier barrier = new CyclicBarrier(2);

        // 创建并启动线程T1
        Thread t1 = new Thread(new MyThread2(barrier), "T1");
        t1.start();

        // 等待线程T1执行完
        barrier.await();

        // 创建并启动线程T2
        Thread t2 = new Thread(new MyThread2(barrier), "T2");
        t2.start();

        // 等待线程T2执行完
        barrier.await();

        // 创建并启动线程T3
        Thread t3 = new Thread(new MyThread2(barrier), "T3");
        t3.start();

        // 等待线程T3执行完
        barrier.await();
    }
}

class MyThread2 implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(MyThread2.class);

    private final CyclicBarrier barrier;

    public MyThread2(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is Running.");
        } catch (InterruptedException e) {
            logger.error("Thread is InterruptedException:{}", e.getMessage());
        } finally {
            // 等待其他线程完成
            try {
                barrier.await();
            } catch (Exception e) {
                logger.error("Thread is Exception:{}", e.getMessage());
            }
        }
    }
}
