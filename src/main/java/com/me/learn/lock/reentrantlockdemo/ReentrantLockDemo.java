/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.lock.reentrantlockdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/23
 **/
public class ReentrantLockDemo {

    private static ReentrantLock reentrantLock = new ReentrantLock(true);
    private static int i;
    public static void increase() {
        reentrantLock.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(50);
           i++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            // 必须在finnally 快释放锁
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10000; j++) {
            new  Thread(()-> {ReentrantLockDemo.increase();}).start();
        }
        
        Thread.sleep(5000);

        System.out.println("i = " + i);
        
    }


}
