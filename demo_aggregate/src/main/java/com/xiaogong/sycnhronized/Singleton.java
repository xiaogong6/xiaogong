package com.xiaogong.sycnhronized;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-18
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton(){

    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        System.out.println("singleton......" + singleton.hashCode());
        return singleton;
    }

    public static void main(String[] args) {
        new Thread(Singleton::getSingleton,"thread1").start();
        new Thread(Singleton::getSingleton,"thread2").start();
    }

}
