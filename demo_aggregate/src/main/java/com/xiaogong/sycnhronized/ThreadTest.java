package com.xiaogong.sycnhronized;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-22
 */
public class ThreadTest {

    public static void main(String[] args) {

        // synchronized的普通方法，其实锁的是具体调用这个方法的实例对象，而synchronized的静态方法，其实锁的是这个方法锁属于的类对象。
        // synchronized(this)，其实锁的是this这个实例对象，而synchronized(Xxx.Class)，其实锁的是这个类对象。
        for (int i = 0; i < 10; i++) {
            MyThreadB myThread = new MyThreadB();
            Thread thread = new Thread(myThread);
            thread.start();

        }
    }

}

class MyThreadA implements Runnable {

    @Override
    public void run() {
        print();
    }

    public static synchronized void print() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis());
    }
}

class MyThreadB implements Runnable {

    @Override
    public void run() {
        print();
    }

    public synchronized void print() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis());
    }
}