/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture;

import java.sql.SQLOutput;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/11
 **/
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                sleep(4000);
                return "Async Thread";
            }
        });
        Thread.sleep(1000);
        System.out.println("Main Thread");
        System.out.println(future.get());
    }

}
