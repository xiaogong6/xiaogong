package com.xiaogong.thread;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-03
 */
public class VirtualThreadDemoStudy {

    public static void main(String[] args) {
        Thread.startVirtualThread(()->{
            System.out.println("虚拟线程");
        });

        Thread.Builder.OfVirtual ofVirtual = Thread.ofVirtual().name("虚拟线程");
        Thread.Builder.OfPlatform ofPlatform = Thread.ofPlatform().name("平台线程");

        ofVirtual.start(()->{});
        ofPlatform.start(()->{});

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10000).forEach(i -> executor.submit(() -> {
                Thread.sleep(Duration.ofSeconds(1));
                return i;
            }));
        }
    }

    @Test
    public void test01(){

        Runnable runnable = getRunnable();

        Instant start = Instant.now();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for(int i = 0; i < 10_000; i++) {
                executor.submit(runnable);
            }
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        // 总耗时 : 1616
        System.out.println("总耗时 : " + timeElapsed);
    }

    @Test
    public void test02() {
        Runnable runnable = getRunnable();
        Instant start = Instant.now();

        try (var executor = Executors.newFixedThreadPool(100)) {
            for(int i = 0; i < 10_000; i++) {
                executor.submit(runnable);
            }
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        // 总耗时 : 101138
        System.out.println("总耗时 : " + timeElapsed);
    }

    private Runnable getRunnable() {
        final AtomicInteger atomicInteger = new AtomicInteger();

        return () -> {
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch(Exception e) {
                System.out.println(e);
            }
            System.out.println("Work Done - " + atomicInteger.incrementAndGet());
        };
    }

    @Test
    public void completableFuture(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

    }

}
