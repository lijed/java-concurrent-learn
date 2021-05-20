/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2020/6/14 10:13
 * Project Name: java-concurrent-learn
 */
public class ScheduleThreadPoolApp {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =  Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(() -> {
            System.out.println("Take a pill every 10 seconds");
        }, 5, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }
}
