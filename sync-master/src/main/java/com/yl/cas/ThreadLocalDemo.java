package com.yl.cas;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {
    public static void main(String[] args) {
        House2 house = new House2();
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                for (int j = 0; j < size; j++) {
                    house.saleHouse();
                    house.saleVolumeByThreadLocal();
                }
                System.out.println(Thread.currentThread().getName() + "\t 号销售卖出====== "+ house.saleVolume.get());
                System.out.println(Thread.currentThread().getName() + "\t 号销售卖出 " + size + "套");
            }, String.valueOf(i)).start();
        }

        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace();}

        System.out.println(Thread.currentThread().getName() + "\t 共计卖出 " + house.saleCount + " 套");
    }
}

/**
 * 资源类
 */
class House2 {
    int saleCount = 0;

    public synchronized void saleHouse() {
        ++saleCount;
    }

    /// jdk8 之前 initialValue() 匿名内部类写法，很冗余且繁琐，不推荐
    // ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>() {
    //     @Override
    //     protected Integer initialValue() {
    //         return 0;
    //     }
    // };

    // jdk8 的 withInitial 写法，推荐
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);

    public void saleVolumeByThreadLocal() {
        saleVolume.set(saleVolume.get() + 1);
    }

}
