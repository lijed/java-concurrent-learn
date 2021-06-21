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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/12
 **/
public class CompletableFutureResultGetTest {

    @Test
    public void testResultGetTimeout() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture completableFuture = CompletableFuture.runAsync(()-> {

            System.out.println("任务开始执行，并进入等待1小时");
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(completableFuture.get(2, TimeUnit.SECONDS));
    }
}
