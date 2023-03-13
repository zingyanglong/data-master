package com.yl.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class AccumulatorCompareDemo {
    public static final int OPSNUMBER = 10000;
    public static final int THREADNUMBER = 50;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();
        long startTime;
        long endTime;
        CountDownLatch countDownLatch1 = new CountDownLatch(THREADNUMBER);
        CountDownLatch countDownLatch2 = new CountDownLatch(THREADNUMBER);
        CountDownLatch countDownLatch3 = new CountDownLatch(THREADNUMBER);
        CountDownLatch countDownLatch4 = new CountDownLatch(THREADNUMBER);

        // 1、Synchronize
        startTime = System.currentTimeMillis();
        for (int i = 0; i < THREADNUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * OPSNUMBER; j++) {
                        clickNumber.clickBySynchronize();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + "毫秒 \t clickBySynchronize：" + clickNumber.number);

        // 2、AtomicLong
        startTime = System.currentTimeMillis();
        for (int i = 0; i < THREADNUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * OPSNUMBER; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + "毫秒 \t clickByAtomicLong：" + clickNumber.atomicLong.get());

        // 3、LongAdder
        startTime = System.currentTimeMillis();
        for (int i = 0; i < THREADNUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * OPSNUMBER; j++) {
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + "毫秒 \t clickByLongAdder：" + clickNumber.longAdder.sum());

        // 4、LongAccumulator
        startTime = System.currentTimeMillis();
        for (int i = 0; i < THREADNUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * OPSNUMBER; j++) {
                        clickNumber.clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + "毫秒 \t clickByLongAccumulator：" + clickNumber.longAccumulator.get());
    }
}

class ClickNumber {
    int number = 0;

    public synchronized void clickBySynchronize() {
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);

    public void clickByAtomicLong() {
        atomicLong.incrementAndGet();
    }

    LongAdder longAdder = new LongAdder();

    public void clickByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public void clickByLongAccumulator() {
        longAccumulator.accumulate(1);
    }
}
