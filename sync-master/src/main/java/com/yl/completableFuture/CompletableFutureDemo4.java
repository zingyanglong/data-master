package com.yl.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo4 {
    public static void main(String[] args) {
        whenCompleteAsyncExtracted();
    }
    private static void whenCompleteAsyncExtracted() {
        CompletableFuture<Integer> completableFuture =CompletableFuture.supplyAsync(() ->{
            System.out.println(Thread.currentThread().getName() + "\t ---come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("---一秒后出结果：" + result);
            return result;
        }).whenCompleteAsync((r, e) -> {
            if (e == null) {
                System.out.println("----计算完成，更新系统updateValue：" + r);
            }
        }).exceptionally(e -> {
            System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "\t ---线程先去忙其他任务");
    }
}
