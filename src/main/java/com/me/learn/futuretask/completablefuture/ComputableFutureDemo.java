/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/9
 **/
public class ComputableFutureDemo {
    public static void main(String[] args) {


        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
            System.out.println("run asyn");
        });

    }

}
