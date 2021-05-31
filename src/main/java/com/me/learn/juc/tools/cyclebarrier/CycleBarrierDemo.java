/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.tools.cyclebarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/30
 **/
public class CycleBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cycleBarrier = new CyclicBarrier(4, () -> {
            System.out.println("worker are done");
            System.out.println(Thread.currentThread().getName());
        });
        for (int i = 0; i < 4; i++) {
            new Worker(cycleBarrier).start();
        }
    }

    private static class  Worker extends  Thread {
        public Worker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        private CyclicBarrier cyclicBarrier;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start execution");
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finish execution");
        }
    }

}
