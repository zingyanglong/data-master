package com.yl.completableFuture;

import java.util.concurrent.*;

public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        m2();
    }

    /** 3个任务，目前开启多个线程来处理，请问耗时多久？ */
    private static void m2() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        FutureTask<String> futureTask1 =
                new FutureTask<String>(
                        () -> {
                            try {
                                TimeUnit.MILLISECONDS.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "task1 over";
                        });
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 =
                new FutureTask<String>(
                        () -> {
                            try {
                                TimeUnit.MILLISECONDS.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "task2 over";
                        });
        threadPool.submit(futureTask2);

        System.out.println("futureTask1.get() = " + futureTask1.get());
        System.out.println("futureTask2.get() = " + futureTask2.get());

        // main 线程
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + "毫秒");
    }

}
