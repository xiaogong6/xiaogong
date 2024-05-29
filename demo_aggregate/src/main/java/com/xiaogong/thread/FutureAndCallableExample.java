package com.xiaogong.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-03
 */
public class FutureAndCallableExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> callable = () -> {
            System.out.println("Entered Callable");
            Thread.sleep(2000);
            return "Hello from Callable";
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("Do something else while callable is getting executed");
        System.out.println("Retrieved: " + futureTask.get());
    }

    @Test
    public void futureAndCallableExample() {
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            Callable<String> callable = () -> {
                System.out.println("Entered Callable");
                Thread.sleep(2000);
                return "Hello from Callable";
            };

            System.out.println("Submitting Callable");
            Future<String> future = executor.submit(callable);

            System.out.println("Do something else while callable is getting executed");
            System.out.println("Retrieved: " + future.get());

            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
