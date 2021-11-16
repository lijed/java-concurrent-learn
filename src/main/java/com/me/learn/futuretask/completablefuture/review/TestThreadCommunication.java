/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture.review;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/10/23
 **/
public class TestThreadCommunication implements  Runnable{
    private CompletableFuture<String> completableFuture;

    public TestThreadCommunication(CompletableFuture<String> completableFuture) {
        this.completableFuture = completableFuture;
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture completableFuture = new CompletableFuture();
        new Thread(new TestThreadCommunication(completableFuture)).start();
        new Thread(new TestThreadCommunication(completableFuture)).start();
        TimeUnit.SECONDS.sleep(5);
        completableFuture.complete("test");
    }

    @SneakyThrows
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " starts to run");
        final String s = completableFuture.get();
        System.out.println(threadName  + " get the Result: " + s);
    }
}
