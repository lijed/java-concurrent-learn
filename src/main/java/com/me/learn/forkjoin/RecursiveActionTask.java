/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/16
 **/
public class RecursiveActionTask extends RecursiveAction {
    private static final int THRESHOLD = 9;
    private int start;
    private int end;

    public RecursiveActionTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i <=end; i++) {
                System.out.println(Thread.currentThread().getName() + "i = " + i);
            }
        } else {
            RecursiveAction recursiveAction1 = new RecursiveActionTask(start, (start + end) / 2);
            RecursiveAction recursiveAction2 = new RecursiveActionTask((start + end) / 2 + 1, end);
            invokeAll(recursiveAction1, recursiveAction2);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        RecursiveActionTask task = new RecursiveActionTask(1, 100);
        forkJoinPool.submit(task);

        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }
}
