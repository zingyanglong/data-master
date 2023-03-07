package com.yl.thread;

import java.util.concurrent.*;

public class TestThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread2 thread2 = new Thread2();
        Thread thread = new Thread(thread2);
        thread.start();

        //thread3
        Thread3 thread3 = new Thread3();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(thread3);
        try {
            Integer sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //thread4
        Thread4 thread4 = new Thread4();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        Future<Thread4> f1 =threadPool.submit(thread4);
        Future<Thread4> f2 =threadPool.submit(thread4);
        Thread4 s6 = f1.get();
        Thread4 s7 = f1.get();

    }
}
