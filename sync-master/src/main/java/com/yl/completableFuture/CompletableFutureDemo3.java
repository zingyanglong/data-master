package com.yl.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        supplyAsyncExtracted();

    }
    private static void supplyAsyncExtracted() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("---一秒后出结果：" + result);
            return result;
        });
        System.out.println(Thread.currentThread().getName() + "\t ---线程先去忙其他任务");

        System.out.println("supplyAsync.get() = " + supplyAsync.get());
    }
}
