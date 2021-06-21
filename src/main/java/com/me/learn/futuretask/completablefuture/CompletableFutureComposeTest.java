/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture;

import org.junit.jupiter.api.Test;
import sun.util.locale.provider.TimeZoneNameUtility;

import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/10
 **/
public class CompletableFutureComposeTest {

    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.runAsync(()-> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenCompose((rs)-> {
            System.out.println(rs);
            return CompletableFuture.supplyAsync(()-> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return  rs + "111111111111111111";
            });
        });

        System.out.println(completableFuture.get());
    }
}
