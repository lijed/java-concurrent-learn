/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/31
 **/
public class FairSynchronousQueueTwoPut {
    public static void main(String[] args) {

         // faire TransferQueue
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<String>(true);
        final int quantity = 10;
        Thread producer = new Thread(() -> {
            for (int i = 0; i < quantity; i++) {
                try {
                    synchronousQueue.put("Producer1 -  bag " + i);
                    System.out.println(Thread.currentThread().getName() + " --Producer1 bag" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer1");
        producer.start();

        Thread producer2 = new Thread(() -> {
            for (int i = 0; i < quantity; i++) {
                try {

                    synchronousQueue.put(" -Producer2 bag " + i);
                    System.out.println(Thread.currentThread().getName() + " Producer2  bag" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer2");

        producer2.start();

        String consumerThreadName =  "consumer thread";
        Thread consumers = new Thread(() -> {
            while (true) {
                try {
                    String element = synchronousQueue.take();
                    System.out.println(consumerThreadName + " is consuming " + element);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, consumerThreadName);
        consumers.start();
    }

}
