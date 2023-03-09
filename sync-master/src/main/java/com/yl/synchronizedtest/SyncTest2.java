package com.yl.synchronizedtest;

public class SyncTest2 {
    // 修饰静态方法
    public static synchronized void test1() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 修饰类对象
    public void test2() {
        synchronized (SyncTest2.class) {
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
                SyncTest2.test1();
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
