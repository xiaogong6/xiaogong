package com.xiaogong.sycnhronized;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-18
 */
public class VolatileTest {

    volatile int number = 0;

    public void increase() {
        number++;
    }

    public static void main(String[] args) {
        VolatileTest volatileAtomDemo = new VolatileTest();
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    volatileAtomDemo.increase();
                }
            }, String.valueOf(j)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() +
                " final number result = " + volatileAtomDemo.number);
    }


}
