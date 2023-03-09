package com.yl.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskIsDone {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        m3();
    }
    private static void m3() throws InterruptedException, ExecutionException, TimeoutException {
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
        // System.out.println(futureTask.get());
        // System.out.println(futureTask.get(2000,TimeUnit.MILLISECONDS));

        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                // 暂停
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在处理中----");
            }
        }
    }
}
