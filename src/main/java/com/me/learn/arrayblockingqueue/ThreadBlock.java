/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Administrator
 * @date 2020/6/1 21:26
 * Project Name: java-concurrent-learn
 */
public class ThreadBlock {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);

        blockingQueue.put("Jed");
        blockingQueue.take(); // 主线程会被阻塞   //wait/notify   | condition .await/signal | Lock
        System.out.println("hello world");
    }
}
