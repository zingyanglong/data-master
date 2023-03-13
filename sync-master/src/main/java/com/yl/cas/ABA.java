package com.yl.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference stampedReference = new AtomicStampedReference(100,1);

    public static void main(String[] args) {
        // abaHappen();
        stampedReferenceMethod();
    }

    private static void stampedReferenceMethod() {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 首次版本号："+stampedReference.getStamp());

            // 暂停500毫秒，确保后面的 t4 线程初始化拿到的版本号和 t3 一样
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

            stampedReference.compareAndSet(100,101,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 第2次版本号："+stampedReference.getStamp());

            stampedReference.compareAndSet(101,100,stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t 第3次版本号："+stampedReference.getStamp());

        },"t3").start();

        new Thread(()->{
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 首次版本号："+stamp);

            // 暂停1秒钟，等待上面的 t3 线程发生 ABA 问题
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}

            boolean b = stampedReference.compareAndSet(100, 2022, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t 修改是否成功："+ b);
            System.out.println(Thread.currentThread().getName() + "\t 第2次版本号："+stampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "\t stampedReference值："+stampedReference.getReference());
        },"t4").start();
    }

}
