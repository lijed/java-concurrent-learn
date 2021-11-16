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
 * Created: 2021/5/30
 **/
public class UnfairSyschronousQueueDemo {

    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<String>(false);
        final int quantity= 10;
        Thread producer = new Thread(()-> {
            for (int i = 0; i < quantity; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " produce  bag"  + i);
                    synchronousQueue.put("bag " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer");

        Thread consumer = new Thread(()-> {
            for (int i = 0; i < quantity; i++) {
                try {
                   String bag =  synchronousQueue.take();
                    System.out.println(Thread.currentThread().getName() + " buys " + bag);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");
        producer.start();
        consumer.start();

    }


}
