package com.yl.synchronizedtest;

public class SynchronizedTest {
    public static void main(String[] args) {
        final  SynchronizedDemo synchronizedDemo=new SynchronizedDemo();
        final  SynchronizedDemo synchronizedDemo1=new SynchronizedDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo1.generalMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo.generalMethod2();
            }
        }).start();
    }
}
