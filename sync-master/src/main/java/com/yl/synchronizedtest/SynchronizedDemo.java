package com.yl.synchronizedtest;

public class SynchronizedDemo {
    public static void main(String[] args) {

        final  SynchronizedDemo synchronizedDemo=new SynchronizedDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo.generalMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo.generalMethod2();
            }
        }).start();
    }

    public synchronized void generalMethod1(){
        for (int i = 0; i < 3; i++) {
            System.out.println("generalMethod1 excute"+i+" time");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void generalMethod2(){
        for (int i = 0; i < 3; i++) {
            System.out.println("generalMethod2 excute"+i+" time");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
