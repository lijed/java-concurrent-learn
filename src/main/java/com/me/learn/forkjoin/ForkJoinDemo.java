/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/6
 **/
public class ForkJoinDemo {

    private static final Integer MAX = 200;

    private static class CalculateForkJoinTask extends RecursiveTask<Integer> {
        private Integer startValue;
        private Integer endValue;

        public CalculateForkJoinTask(Integer startValue, Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        protected Integer compute() {

            System.out.println(Thread.currentThread().getName() + " calculates  [" + startValue + "," + endValue + "]");
            if (endValue - startValue <= MAX) {
                int total = 0;
                for (int i = startValue; i <= endValue; i++) {
                    total += i;
                }
                return total;
            }

            CalculateForkJoinTask subTask1 = new CalculateForkJoinTask(startValue, (startValue + endValue) / 2);
            subTask1.fork();
            CalculateForkJoinTask subTask2 = new CalculateForkJoinTask((startValue + endValue) / 2 + 1, endValue);
            subTask2.fork();
            return subTask1.join() + subTask2.join();
        }
    }

    public static void main(String[] args) {
        CalculateForkJoinTask calculateForkJoinTask = new CalculateForkJoinTask(0, 100000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(calculateForkJoinTask);
        try {
            Integer result = forkJoinTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
