/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.forkjoin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/6
 **/
public class ForkJoinPoolTest {
    private static final int threads = 10;
    CountDownLatch countDownLatch = new CountDownLatch(threads);

    @Test
    public void test1() throws InterruptedException {
        System.out.println("-------- begin ----------");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < threads; i++) {
            forkJoinPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("getParallelism=" + forkJoinPool.getParallelism());
                        System.out.println("getStealCount=" + forkJoinPool.getStealCount());
                        System.out.println(Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        System.out.println("-------- end ----------");
    }

    @Test
    public void testInvoke() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(ForkJoinTask.adapt(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test");
            }

        }));
    }

    @Test
    public void testExecuteRecursiveAction() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new RecursiveAction() {
            @Override
            protected void compute() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Exeution is done");
            }
        });
        forkJoinPool.shutdown();
//        try {
////            forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
