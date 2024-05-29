package com.xiaogong.sycnhronized;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-18
 */
public class CASTest {
    public static void main(String[] args) {
        String initialRef = "xxx";
        int initialStamp = 0;

        AtomicStampedReference<String> atomicStampedRef =
                new AtomicStampedReference<>(initialRef, initialStamp);

        String newRef = "xxx_xxx";
        int newStamp = initialStamp + 1;

        boolean updated = atomicStampedRef.compareAndSet(initialRef, newRef, initialStamp, newStamp);
        System.out.println("Updated: " + updated);
    }

}
