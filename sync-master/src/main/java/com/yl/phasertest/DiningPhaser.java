package com.yl.phasertest;

import java.util.concurrent.Phaser;

public class DiningPhaser  extends Phaser {
    /**
     * 每一阶段到达时，都会执行这个方法，执行完后 phase 自动加1，
     * 代表进入下一阶段
     *
     * @param phase             代表哪个阶段，从0开始
     * @param registeredParties 注册的任务
     * @return 是否终止
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                System.out.println("第一阶段，买食材完成啦！总共参与人数：" +
                        registeredParties);
                return false;
            case 1:
                System.out.println("第二阶段，炒菜完成啦！总共参与人数：" +
                        registeredParties);
                return false;
            case 2:
                System.out.println("第三阶段，吃完饭啦！总共参与人数：" +
                        registeredParties);
                return false;
            default:
                return true;
        }
    }
}
