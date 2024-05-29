package com.xiaogong.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-19
 */
public class SharedDataExample {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> sharedData = new ConcurrentHashMap<>();
        SonThread thread = new SonThread(sharedData);
        thread.start();
        sharedData.put("key", "value");
        System.out.println("sharedData in main thread: " + sharedData.get("key"));
    }

    @Test
    public void test() {
        InheritableThreadLocal<Integer> sharedData = new InheritableThreadLocal<>();

    }
}

class SonThread extends Thread {
    ConcurrentHashMap<String, String> sharedData;

    public SonThread(ConcurrentHashMap<String, String> data) {
        this.sharedData = data;
    }

    @Override
    public void run() {
        sharedData.put("key", "new value");
        System.out.println("sharedData in child thread: " + sharedData.get("key"));
    }

}
