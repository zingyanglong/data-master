package com.yl.thread;

public class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("继承Thread创建一个线程");
    }
}
