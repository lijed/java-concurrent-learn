/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.priorityqueue;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/11/16
 **/
public class PriorityQueueDemo {
    static PriorityBlockingQueue contaner = new PriorityBlockingQueue();

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            contaner.put("task" + random.nextInt(20));
        }

        for (Object o : contaner) {
            System.out.println(o);
        }
    }
}
