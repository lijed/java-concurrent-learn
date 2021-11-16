/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.delayedworkqueue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/27
 **/
public class DelayWorkQueueDemo {
    public static void main(String[] args) throws ExecutionException {
        AtomicInteger threadCount = new AtomicInteger();

        //ScheduledExecutorService 内部使用DelayWorkQueue来存储要提交的任务（带返回值和不呆返回值的）
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


        ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "hello" + System.currentTimeMillis();
        }, 10, TimeUnit.SECONDS);

        try {
            String result = scheduledFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AtomicInteger count = new AtomicInteger();
        ScheduledFuture<?> scheduledFuture1 = scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("periodically execute every 20 seconds");
            count.getAndIncrement();
        }, 2, 3, TimeUnit.SECONDS);

        while (true) {
            Object o = null;
            try {
                System.out.println("获取定时任务的结果");
                o = scheduledFuture1.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(o.toString());
        }

    }
}
