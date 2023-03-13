package com.yl.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.compareAndSet(10,20);
    }
}
