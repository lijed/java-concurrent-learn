/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * @date 2020/6/1 23:15
 * Project Name: java-concurrent-learn
 */
public class CountdownLatchDemo1 implements Runnable{
    static CountDownLatch countDownLatch=new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(new CountdownLatchDemo1(), "Thread-" + i).start();
        }
        //TODO ..
        countDownLatch.countDown(); //1-0
    }
    @Override
    public void run() {
        try {
            countDownLatch.await(); //阻塞线程| 1000个线程阻塞
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
