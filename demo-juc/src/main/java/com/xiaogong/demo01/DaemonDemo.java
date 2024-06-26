package com.xiaogong.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @Program: xiaogong
 * @Description:
 * @Author: xiongke
 * @Create: 2024-06-26
 */
public class DaemonDemo {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t running" +
                    (Thread.currentThread().isDaemon() ? "守护线程" : "用户线程"));
            while (true) {
                // System.out.println("线程活着");
            }
        }, "t1");
        thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "\t主线程结束");
    }

}
