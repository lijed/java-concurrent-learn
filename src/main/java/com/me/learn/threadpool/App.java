/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @date 2020/6/11 22:49
 * Project Name: java-concurrent-learn
 */
public class App implements  Runnable{

    public static void main(String[] args) {
        ExecutorService executorService =  Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new App());
        }

      /*  Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(5);*/
    }

    //阻塞队列
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        while(true) {
            //执行任务
        }
    }
}
