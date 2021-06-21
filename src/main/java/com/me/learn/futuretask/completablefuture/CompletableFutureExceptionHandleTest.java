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
public class CompletableFutureExceptionHandleTest {
    @Test
    public void testwhenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.runAsync(()-> {
            int result = 1/0;
        }).whenComplete((result, e)-> {
            if (e != null) {
                e.printStackTrace();

            } else {
                System.out.println(result);
            }
        });

        System.out.println(completableFuture.get());
    }

    @Test
    public void testwhenCompleteWithReturnResult() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.runAsync(()-> {
            throw new RuntimeException("Error input");
        }).whenComplete((result, e)-> {
            if (e != null) {
                System.out.println("Exception Information: ");
                e.printStackTrace();

            } else {
                System.out.println(result);
            }
        });

        System.out.println("result: " + "\r\n" + completableFuture.get());
    }

    @Test
    public void testHandle() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> completableFuture = CompletableFuture.runAsync(()-> {
            int result = 1/0;
        }).handle((rs, e)-> {
            if (e != null) {
                e.printStackTrace();
            }
            return e;
        });

        System.out.println(completableFuture.get());
    }

    @Test
    public void testExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()-> {
            int result = 1/0;
            return "hello";
        }).exceptionally(e->{
           return "稍后再试";
        });

        System.out.println(completableFuture.get());
    }
}
