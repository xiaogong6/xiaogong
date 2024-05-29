package com.xiaogong.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Program: demo-java
 * @Description: 死锁
 * @Author: xiongke
 * @Create: 2024-04-15
 */
public class MayDeadLock {

    private final static Logger LOGGER = LoggerFactory.getLogger(MayDeadLock.class);

    final Object o1 = new Object();

    final Object o2 = new Object();

    public void thread1() throws InterruptedException {

        synchronized (o1) {

            Thread.sleep(500);

            synchronized (o2) {

                System.out.println("线程1成功拿到两把锁");

            }

        }

    }

    public void thread2() throws InterruptedException {

        synchronized (o2) {

            Thread.sleep(500);

            synchronized (o1) {

                System.out.println("线程2成功拿到两把锁");

            }

        }

    }

    public static void main(String[] args) {

        MayDeadLock mayDeadLock = new MayDeadLock();

        new Thread(new Runnable() {

            @Override

            public void run() {

                try {

                    mayDeadLock.thread1();

                } catch (InterruptedException e) {
                    LOGGER.error("mayDeadLock.thread1() error", e);
                }

            }

        }).start();

        new Thread(new Runnable() {

            @Override

            public void run() {

                try {

                    mayDeadLock.thread2();

                } catch (InterruptedException e) {
                    LOGGER.error("mayDeadLock.thread2() error", e);

                }

            }

        }).start();

    }

}
