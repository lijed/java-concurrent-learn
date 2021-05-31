/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.tools.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 *
 *  实现的场景：一个线程必须等他其他的线程里的任务执行完了才能执行。
 *
 * @author Administrator
 * @date 2020/6/1 23:07
 * Project Name: java-concurrent-learn
 */
public class CountDownDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + " -> Begin");
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " -> End");
        }, "thread1").start();

        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + " -> Begin");
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " -> End");
        }, "Thread2").start();

        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + " -> Begin");
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " -> End");
        }, "Thread3").start();

        countDownLatch.await(); //阻塞main Thread

        System.out.println("主线程执行结束");
    }
}

