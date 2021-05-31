/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/27
 **/
public class DelayQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Task> queue = new DelayQueue<>();
        for (int i = 10; i > 0; i--) {
            queue.add(new Task(i, "task" + i));
        }
        System.out.println("共有元素个数："  + queue.size());
        while(true) {
           Task task =  queue.take();
            System.out.println(task.toString() + "available time: " + task.getAvailableTime());
            if(queue.isEmpty()) {
                break;
            }
        }
    }
}
