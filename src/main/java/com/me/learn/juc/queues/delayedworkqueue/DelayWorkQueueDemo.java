/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.delayedworkqueue;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/27
 **/
public class DelayWorkQueueDemo {
    public static void main(String[] args) {
        AtomicInteger threadCount = new AtomicInteger();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "schedule thread" + threadCount.incrementAndGet());
            }
        });

        scheduledExecutorService.schedule(() -> {
            System.out.println("print job, delay 5 秒, " + Thread.currentThread().getName());
        }, 5, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> {
            System.out.println("print job, delay 10 秒 " + Thread.currentThread().getName());
        }, 10, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }
}
