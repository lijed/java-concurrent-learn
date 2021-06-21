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
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/10
 **/
public class ComputableFutureTest {

    @Test
    public void testAsynRun() {
        printCurrentTheadName(Thread.currentThread().getName());
        CompletableFuture.runAsync(() -> {
            printCurrentTheadName(Thread.currentThread().getName());
            printCurrentTheadName("run Async");
        });
    }

    @Test
    public void testAsynRunWIthExecutor() throws ExecutionException, InterruptedException {
        printCurrentTheadName(Thread.currentThread().getName());
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            printCurrentTheadName(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printCurrentTheadName("run Async");
        }, Executors.newFixedThreadPool(3));

        System.out.println(cf.get());
    }

    @Test
    public void testSupplyAsyn() throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    printCurrentTheadName(Thread.currentThread().getName());
                    return "hello world";
                }
        ).thenApplyAsync(rs -> {
            printCurrentTheadName(Thread.currentThread().getName());
            return rs + " jed";
        });

        System.out.println(cf.get());
    }


    @Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printCurrentTheadName(Thread.currentThread().getName());
            return "SupplyAsync With result";
        }).thenApply(rs -> {
            printCurrentTheadName(Thread.currentThread().getName());
            return "apply  " + rs;
        });

        System.out.println("processing other logics");
        String result = completableFuture.get();
        System.out.println(result);
    }


    @Test
    public void testAsynApplyAndPostValue() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printCurrentTheadName(Thread.currentThread().getName());
            return "SupplyAsync With result";
        });
        CompletableFuture cf = completableFuture.whenComplete((rs, exception) -> {
            if (exception != null) {
                System.out.println("loging");
                System.out.println("Exception with execuing");
            } else {
                System.out.println("rs = " + rs);
                System.out.println("do other logics.....");
            }
        });
        Object result = cf.get();
        System.out.println("result: " + result);

        cf = completableFuture.whenCompleteAsync((rs, exception) -> {
            if (exception != null) {
                System.out.println("loging");
                System.out.println("Exception with execuing");
            } else {
                System.out.println("rs = " + rs);
                System.out.println("do other logics.....");
            }
        });
        result = cf.get();
        System.out.println("result: " + result);

    }

    @Test
    public void testThreadCommunication() {
        final CompletableFuture cf = new CompletableFuture<String>();

        new Thread(() -> {
            Object result = null;
            try {
                result = cf.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }).start();
        new Thread(() -> {
            Object result = null;
            try {
                result = cf.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cf.complete("result");
    }

    private void printCurrentTheadName(String name) {
        System.out.println(name);
    }
}
