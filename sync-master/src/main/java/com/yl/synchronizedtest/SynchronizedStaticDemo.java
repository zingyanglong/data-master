package com.yl.synchronizedtest;

public class SynchronizedStaticDemo {
    public static void main(String[] args) {
        final  SynchronizedStaticDemo synchronizedStaticDemo=new SynchronizedStaticDemo();
        final  SynchronizedStaticDemo synchronizedStaticDemo1=new SynchronizedStaticDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedStaticDemo.generalMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedStaticDemo1.generalMethod2();
            }
        }).start();
    }

    public synchronized static void generalMethod1(){
        for (int i = 0; i < 3; i++) {
            System.out.println("generalMethod1 excute"+i+" time");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized  static void generalMethod2(){
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
