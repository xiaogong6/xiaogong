package com.xiaogong.arrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-02
 */
public class CustomThreadPoolExample {

    public static final Logger logger = LoggerFactory.getLogger(CustomThreadPoolExample.class);

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        try {
            forkJoinPool.submit(() -> Stream.of("Apple", "Banana", "Orange", "Watermelon")
                    .parallel().forEach(System.out::println)).get();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            forkJoinPool.shutdown();
        }
    }

}
