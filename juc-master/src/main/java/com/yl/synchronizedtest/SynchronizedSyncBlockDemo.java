package com.yl.synchronizedtest;

public class SynchronizedSyncBlockDemo {
    Object lockA=new Object();

    public static void main(String[] args) {

        final  SynchronizedSyncBlockDemo synchronizedDemo=new SynchronizedSyncBlockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo.blockMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDemo.blockMethod2();
            }
        }).start();


    }

    public   void blockMethod1(){
        synchronized (lockA){
            for (int i = 0; i < 3; i++) {
                System.out.println("generalMethod1 excute"+i+" time");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public    void blockMethod2(){
        synchronized (lockA) {
            for (int i = 0; i < 3; i++) {
                System.out.println("generalMethod2 excute" + i + " time");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
