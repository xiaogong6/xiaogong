package com.xiaogong.sycnhronized;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-18
 */
public class SingletonCAS {


    private static final AtomicReference<SingletonCAS> INSTANCE = new AtomicReference<>();

    private SingletonCAS() {}

    public static SingletonCAS getInstance() {
        for (;;) {
            SingletonCAS singleton = INSTANCE.get();
            if (null != singleton) {
                return singleton;
            }

            singleton = new SingletonCAS();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }

}
