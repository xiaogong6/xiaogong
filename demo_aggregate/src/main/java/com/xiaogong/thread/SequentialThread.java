package com.xiaogong.thread;

import org.junit.jupiter.api.Test;

/**
 * @Program: demo-java
 * @Description: t1->t2->t3 顺序执行
 * @Author: xiongke
 * @Create: 2024-04-09
 */
public class SequentialThread {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1 running");
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 running");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 3 running");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    @Test
    public void test01() {
        final Thread thread1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " is Running."), "T1");


        final Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                System.out.println("join thread1 failed");
            }
            System.out.println(Thread.currentThread().getName() + " is Running.");
        }, "T2");

        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                System.out.println("join thread1 failed");
            }
            System.out.println(Thread.currentThread().getName() + " is Running.");
        }, "T3");

        thread3.start();
        thread2.start();
        thread1.start();
    }

}
