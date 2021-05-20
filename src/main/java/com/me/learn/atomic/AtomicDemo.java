/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @date 2020/6/5 6:58
 * Project Name: java-concurrent-learn
 */
public class AtomicDemo {
    private static AtomicInteger i  = new AtomicInteger(0);
    private static int count = 0;
    public static void incr() {
        i.getAndIncrement();
    }

    public static void nonAtomicIncr() {
        count++;
    }
    public static void main(String[] args) throws InterruptedException {

//        for (int j = 0; j < 1000; j++) {
//            new Thread(()-> AtomicDemo.incr()).start();
//        }
//
//        Thread.sleep(4000);
//
//        System.out.println("i = " + i.get());

        for (int j = 0; j < 10000; j++) {
            new Thread(()-> AtomicDemo.nonAtomicIncr()).start();
        }

        Thread.sleep(4000);
        System.out.println("count = " + count);
    }
}
