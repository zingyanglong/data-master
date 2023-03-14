package com.yl.phasertest;

import java.util.concurrent.Phaser;

public class XiaoMeiTask implements Runnable{
    private Phaser phaser;

    public XiaoMeiTask(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +
                " 买好白菜了...");
        // 第一阶段的事干完了，等待其他人完成才能进入下一阶段
        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName() +
                " 炒好白菜了...");
        // 第二阶段的事干完了，等待其他人完成才能进入下一阶段
        phaser.arriveAndAwaitAdvance();

        System.out.println(Thread.currentThread().getName() +
                " 吃饱了...");
        // 第三阶段的事干完了，等待其他人完成才能进入下一阶段
        phaser.arriveAndAwaitAdvance();
    }
}
