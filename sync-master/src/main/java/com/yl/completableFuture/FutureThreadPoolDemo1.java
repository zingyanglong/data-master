package com.yl.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FutureThreadPoolDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
         m1();
    }

    /** 3个任务，目前只有一个线程【main】来处理，请问耗时多久？ */
    private static void m1() {
        long startTime = System.currentTimeMillis();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + "毫秒");
    }
}
