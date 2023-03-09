package com.yl.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureThreadPoolDemo2 {
    public static void main(String[] args)
            throws ExecutionException, InterruptedException, TimeoutException {
        m3();
    }

    private static void m3() throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask =
                new FutureTask<String>(
                        () -> {
                            System.out.println(Thread.currentThread().getName() + "\t ---come in");
                            try {
                                TimeUnit.MILLISECONDS.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "task3 over";
                        });

        Thread t3 = new Thread(futureTask, "t3");
        t3.start();


        System.out.println(Thread.currentThread().getName() + "\t ---忙其他任务了");

        System.out.println(futureTask.get());


    }
}
