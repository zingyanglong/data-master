package com.yl.phasertest;

public class ChenPiMain {
    public static void main(String[] args) {

        // 创建吃饭阶段器，注册3个任务（人）
        DiningPhaser diningPhaser = new DiningPhaser();
        diningPhaser.bulkRegister(3);

        // 三个人同时开始干活
        Thread thread1 = new Thread(new ChenPiTask(diningPhaser));
        thread1.setName("陈皮");
        thread1.start();

        Thread thread2 = new Thread(new XiaoMeiTask(diningPhaser));
        thread2.setName("小美");
        thread2.start();

        Thread thread3 = new Thread(new XiaoXueTask(diningPhaser));
        thread3.setName("小雪");
        thread3.start();
    }
}
