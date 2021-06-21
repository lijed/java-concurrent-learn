/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/11
 **/
public class CompletableFutureAllAnyTest {

    @Test
    public void testAllFuture() throws ExecutionException, InterruptedException {
        // 直接创建一个已经做完的蛋糕
        CompletableFuture c1 = CompletableFuture.completedFuture("cake");
        // 无返回值异步任务，会采用内部forkjoin线程池
        CompletableFuture c2 = CompletableFuture.runAsync(() -> {
        });
        // 无返回值异步任务，采用定制的线程池
        CompletableFuture c3 = CompletableFuture.runAsync(() -> {
        }, newFixedThreadPool(2));
            // 返回值异步任务，采用定制的线程
        CompletableFuture c4 = CompletableFuture.supplyAsync(() -> "cake", newSingleThreadExecutor());
        // 返回值异步任务，采用内部forkjoin线程池
        CompletableFuture c5 = CompletableFuture.supplyAsync(() -> "cake");

        CompletableFuture result = CompletableFuture.allOf(c1, c2, c3, c4, c5);

        System.out.println("do other things");
        System.out.println(result.get());
    }

    @Test
    public void testAllFutureWithJoin() throws ExecutionException, InterruptedException {
        // 直接创建一个已经做完的蛋糕
        CompletableFuture c1 = CompletableFuture.completedFuture("cake");
        // 无返回值异步任务，会采用内部forkjoin线程池
        CompletableFuture c2 = CompletableFuture.runAsync(() -> {
        });
        // 无返回值异步任务，采用定制的线程池
        CompletableFuture c3 = CompletableFuture.runAsync(() -> {}, newFixedThreadPool(2));
        // 返回值异步任务，采用定制的线程
        CompletableFuture c4 = CompletableFuture.supplyAsync(() -> "cake", newSingleThreadExecutor());
        // 返回值异步任务，采用内部forkjoin线程池
        CompletableFuture c5 = CompletableFuture.supplyAsync(() -> "cake");

        CompletableFuture result = CompletableFuture.allOf(c1, c2, c3, c4, c5);
        result.join();
        System.out.println("do other things");
        System.out.println(result.get());
    }

    @Test
    public void testAllFutureWithGetTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        // 直接创建一个已经做完的蛋糕
        CompletableFuture c1 = CompletableFuture.completedFuture("cake");
        // 无返回值异步任务，会采用内部forkjoin线程池
        CompletableFuture c2 = CompletableFuture.runAsync(() -> {
        });
        // 无返回值异步任务，采用定制的线程池
        CompletableFuture c3 = CompletableFuture.runAsync(() -> {}, newFixedThreadPool(2));
        // 返回值异步任务，采用定制的线程
        CompletableFuture c4 = CompletableFuture.supplyAsync(() -> "cake", newSingleThreadExecutor());
        // 返回值异步任务，采用内部forkjoin线程池
        CompletableFuture c5 = CompletableFuture.supplyAsync(() -> "cake");

        CompletableFuture result = CompletableFuture.allOf(c1, c2, c3, c4, c5);
        System.out.println("do other things");
        result.get(1, TimeUnit.HOURS);
    }
    @Test
    public void testAnyOfFuture() throws ExecutionException, InterruptedException, TimeoutException {
        // 直接创建一个已经做完的蛋糕
        CompletableFuture c1 = CompletableFuture.completedFuture("cake");
        // 无返回值异步任务，会采用内部forkjoin线程池
        CompletableFuture c2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 无返回值异步任务，采用定制的线程池
        CompletableFuture c3 = CompletableFuture.runAsync(() -> {}, newFixedThreadPool(2));
        // 返回值异步任务，采用定制的线程
        CompletableFuture c4 = CompletableFuture.supplyAsync(() -> "cake", newSingleThreadExecutor());
        // 返回值异步任务，采用内部forkjoin线程池
        CompletableFuture c5 = CompletableFuture.supplyAsync(() -> "cake");

        CompletableFuture result = CompletableFuture.anyOf(c1, c2, c3, c4, c5);
        System.out.println("do other things");
        result.get(1, TimeUnit.HOURS);
    }
}
