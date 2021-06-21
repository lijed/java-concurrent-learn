/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/10
 **/
public class CompletableFutureWithResultTest {
    @Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            return "first method";
        }).thenApply(rs -> {
            rs = "processing" + rs;
            return rs;
        });

        System.out.println(completableFuture.get());
    }

    @Test
    public void testThenApplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            return "first method";
        }).thenApplyAsync(rs -> {
            printCurrentTheadName();
            rs = "processing" + rs;
            return rs;
        });

        System.out.println(completableFuture.get());
    }

    @Test
    public void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            return Integer.valueOf(100);
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        }), (rs1, rs2) -> {
            printCurrentTheadName();
            Integer result = rs1 + rs2;
            System.out.println("result = " + result);
            return result;
        });


        Integer result = completableFuture.get();
        System.out.println("final result = " + result);
    }

    @Test
    public void testApplyToEither() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            return Arrays.asList("Math", "English");
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            return Arrays.asList("TableTennis");
        }), (rs) -> {
            List<String> allLectures = new ArrayList<String>(rs);
            allLectures.add("Computer Science");
            return allLectures;
        }).get().stream().forEach(item -> {
            System.out.println("I like Lectures: ");
            System.out.println(item);
        });
    }




    private void printCurrentTheadName() {
        System.out.println(Thread.currentThread().getName());
    }
}
