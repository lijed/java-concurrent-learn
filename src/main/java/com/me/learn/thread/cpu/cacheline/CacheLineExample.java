/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.thread.cpu.cacheline;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/21
 **/
public class CacheLineExample {
    private static final int TIMES = 650000;

    static void originalMethod() throws InterruptedException {
        long[] arr = new long[2];
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < TIMES; i++) {
                arr[0] = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < TIMES; i++) {
                arr[1] = i;
            }
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("originalMethod :" + (end - start));
    }

    static class Param {
        long l1, l2, l3, l4, l5, l6, l7;
        long num;
    }

    static void optimizedMethod() throws InterruptedException {
        Param[] arr = {new Param(), new Param()};
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < TIMES; i++) {
                arr[0].num = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < TIMES; i++) {
                arr[1].num = i;
            }
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();
        System.out.println("optimizedMethod :" + (end - start));
    }


    public static void main(String[] args) throws InterruptedException {
        originalMethod();
        optimizedMethod();
    }
}
