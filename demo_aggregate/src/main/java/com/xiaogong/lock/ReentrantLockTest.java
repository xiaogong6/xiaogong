package com.xiaogong.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-19
 */
public class ReentrantLockTest {

    private final Lock lock = new ReentrantLock();

    private int count;


    public void increment(int i) {
        lock.lock();
        try {
            count +=i;
        } finally {
            lock.unlock();
        }
    }
}
