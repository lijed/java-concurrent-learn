/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.linkedtransferqueue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/11/15
 **/
public class AyncLinkedTransferQueueDemo {

    private static LinkedTransferQueue<String> container = new LinkedTransferQueue<>();

    static {
        new Thread(()-> {

            for (int i = 0; i < 5; i++) {
                container.put("Task " + i);
            }

        }, "producer 1").start();

    }

    public static void consume() {
        Thread consumer = new Thread(()-> {
           while(!Thread.currentThread().isInterrupted()) {
               String task = null;
               try {
                   task = container.take();
                   System.out.println("consumer task:  " + task);
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
        });
        consumer.start();
    }

    public static void main(String[] args) {
//        consume();
    }

}
