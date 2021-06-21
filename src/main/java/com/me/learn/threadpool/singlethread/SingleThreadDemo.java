/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.threadpool.singlethread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果没有异常中断线程，一直使用这个线程来执行， 如果线程被中断，创建一个新的线程继续执行
 */
public class SingleThreadDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        int taskTotal = 100;
        for (int i = 1; i <= taskTotal; i++) {
            executorService.execute(new Task(i));
        }
    }

    private static class Task implements Runnable {
        private final int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            if (i%5 == 0) {
                throw new RuntimeException("Runtime exception");
            }

            System.out.println("finalI = " + i + ", print by thread " + Thread.currentThread().getName());
        }
    }
}
