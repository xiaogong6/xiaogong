package com.xiaogong.completableFuture;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-04-19
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<CompletableFuture<Integer>> futures = nums.stream()
                .map(value -> CompletableFuture.supplyAsync(() -> {
                    // 这里是每个异步任务要执行的操作，
                    return value;
                }))
                .toList();

        CompletableFuture<Integer> sumFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApplyAsync(v -> {
                    // 所有异步计算任务完成后，将它们的结果进行合并
                    return futures.stream()
                            .mapToInt(CompletableFuture::join)
                            .sum();
                });
        int sum = sumFuture.join();
        System.out.println(sum);
    }

    @Test
    public void test() {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.thenAcceptAsync(result -> System.out.println("Event 1 processed: " + result));
        future.thenAcceptAsync(result -> System.out.println("Event 2 processed: " + result));
        future.thenAcceptAsync(result -> System.out.println("Event 3 processed: " + result));
        future.complete("Hello, CompletableFuture!");
    }

    @Test
    public void test2() {
        Executors.newFixedThreadPool(10);
    }


}
