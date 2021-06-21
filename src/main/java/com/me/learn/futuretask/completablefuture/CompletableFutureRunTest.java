/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/10
 **/
public class CompletableFutureRunTest {
    @Test
    public void testRunAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completedFuture = CompletableFuture.runAsync(() -> {
            System.out.println("RunAsync");
        });
        System.out.println(completedFuture.get());
    }

    @Test
    public void testThenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completedFuture = CompletableFuture.runAsync(() -> {
            System.out.println("RunAsync");
        }).thenRun(() -> {
            System.out.println("Synchoronize Execute a function");
        });
        completedFuture.get();
    }
    @Test
    public void testThenSyncRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completedFuture = CompletableFuture.runAsync(() -> {
            System.out.println("RunAsync");
        }).thenRunAsync(() -> {
            System.out.println("Synchoronize Execute a function");
        });
        completedFuture.get();
    }

    @Test
    public void testTRunAfterBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completedFuture = CompletableFuture.runAsync(() -> {
            System.out.println("RunAsync");
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            return "aaaa";
        }), () -> {
            System.out.println("two completableStages executes completed.");
            System.out.println("Personal logic executing");
        });
        completedFuture.get();
    }


    @Test
    public void testRunAfterEither() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Load customer data......");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).runAfterEitherAsync(CompletableFuture.supplyAsync(() -> {
                    System.out.println("second completionstage is executing...");
                    return "Jed li";
                }),
                () -> {
                    System.out.println("processing.....");
                }
        );
        System.out.println(completableFuture.get());
    }
}
