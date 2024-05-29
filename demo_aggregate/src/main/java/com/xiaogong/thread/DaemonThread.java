package com.xiaogong.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-02
 */
public class DaemonThread {

    public static final Logger logger = LoggerFactory.getLogger(DaemonThread.class);

    /**
     * main执行完成后,由于childThread是守护线程直接退出
     * @param args 入参
     */
    public static void main(String[] args) {
        Thread childThread = new Thread(() -> {
            while (true) {
                System.out.println("I am is child thread");
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
        childThread.setDaemon(true);
        childThread.start();
        System.out.println("I am main thread");
    }

}
