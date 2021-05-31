/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.thread.threadsafe;

/**
 * Volatile 修饰的变量可以保证可见性，但是不能保证原子性，要是实现原子性，必须要加锁
 *
 * @Author: Administrator
 * Created: 2021/5/22
 **/
public class VolatileTest {
    public static volatile  int race = 0;

    public static void increase() {
        race++;
    }

    private static int THREAD_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(()-> {
                for (int j = 1; j <= 10000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(race);
    }
}
