package com.yl.synchronizedtest;

public class SyncTest1 {
    // synchronized修饰非静态方法
    public synchronized void test1() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // synchronized代码块
    public void test2() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SyncTest1 t1 = new SyncTest1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t1.test1();
            }
        }, "thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                t1.test2();
            }
        }, "thread2").start();
    }
}
