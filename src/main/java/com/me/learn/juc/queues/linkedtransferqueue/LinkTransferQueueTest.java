/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.linkedtransferqueue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedTransferQueue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/11/15
 **/
public class LinkTransferQueueTest {

    @Test
    public void testTryTransfer() {
        LinkedTransferQueue<String> container = new LinkedTransferQueue<>();
        boolean task1 = container.tryTransfer("task1");
        System.out.println("is add success? " + task1);
    }

    @Test
    public void testTryTransferWithConsumerThread() throws InterruptedException {
        LinkedTransferQueue<String> container = new LinkedTransferQueue<>();
        new Thread(()-> {
            try {
                container.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        Thread.sleep(1000);
        boolean task1 = container.tryTransfer("task1");
        System.out.println("is add success? " + task1);
    }


    @Test
    public void testFirstConsumer() throws InterruptedException {
        LinkedTransferQueue<String> container = new LinkedTransferQueue<>();
        container.take();
    }
}
