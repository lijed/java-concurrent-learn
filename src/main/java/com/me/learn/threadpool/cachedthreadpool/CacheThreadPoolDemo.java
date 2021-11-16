/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.threadpool.cachedthreadpool;

import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/6
 **/
public class CacheThreadPoolDemo {
    public static void main(String[] args) {

//        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
        final ExecutorService executorService = Executors.newCachedThreadPool();
        int taskCount = 50;
        for (int i = 0; i < taskCount; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Task" + finalI + "executing by thread " + Thread.currentThread());
                    try {
                           TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();

    }
}
