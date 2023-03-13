package com.yl.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    AtomicReference<Thread> atomicReference1 = new AtomicReference();
    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t ---come in");
        while (!atomicReference.compareAndSet(null, thread)) {}
    }


    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t ---task over,unlock");
    }
    public  void  lock1(){
        Thread thread = Thread.currentThread();
        atomicReference1.compareAndSet(null,thread);
        System.out.println(Thread.currentThread().getName() + "\t ---task over,unlock");
    }

    public void unlock1(){
        Thread thread = Thread.currentThread();
        atomicReference1.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t ---task over,unlock");
    }
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.lock();
            // 持有锁5秒钟
            //try { TimeUnit.MILLISECONDS.sleep(5000); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("A thread end");
            spinLockDemo.unlock();
        },"A").start();

        // 暂停500毫秒，线程A先于B之前启动
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(()->{
            spinLockDemo.lock();
            try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("SpinLockDemo.main");
            spinLockDemo.unlock();
        },"B").start();
    }
}
