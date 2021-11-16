/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture.review;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/10/23
 **/
public class CompletableFutureReview {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        final CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(4000);
                printExecutingThreadName();
                System.out.println("do query some data from database");
                countDownLatch.countDown();
            }
        }, Executors.newFixedThreadPool(5));
        voidCompletableFuture.thenRun(()-> {
            printExecutingThreadName();
            System.out.println("start second processing");
            countDownLatch.countDown();
        });

        countDownLatch.await();
    }

    @Test
    public void testBothRun() throws ExecutionException, InterruptedException {
        CompletableFuture task1 = CompletableFuture.supplyAsync(()-> {
            System.out.println("excuting task 1");
            return "task1 result";
        });
        final CompletableFuture<String> completableFuture = task1.thenCombineAsync(CompletableFuture.<String>supplyAsync(() -> {
            System.out.println("Executing task2");
            return "task2 result";
        }), (r1, r2)-> {return r1 + " " + r2;});


        System.out.println(completableFuture.get());
    }

    @Test
    public void testSyn() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.runAsync(()-> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("excuting task1");
        }).thenApply((VOID)-> {
           return "11111";
        });
        System.out.println(completableFuture.get());
        System.out.println("Executing is done");
    }

    @Test
    public void testEither() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()-> {
            System.out.println("1111111111111111");
            return "task1's result";
        });

        final CompletableFuture<Void> voidCompletableFuture = completableFuture.runAfterEither(CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("start to task2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }), () -> {
            System.out.println("start task3");
        });
    }

    @Test
    public void testRunboth() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> System.out.println("run task 1"));
        final CompletableFuture<Void> finnalCompletable= voidCompletableFuture.runAfterBoth(CompletableFuture.runAsync(() -> {
            System.out.println("task2");
        }), () -> {
            System.out.println("task3");
        });



    }



    public void printExecutingThreadName() {
        System.out.println(Thread.currentThread().getName());
    }
}
