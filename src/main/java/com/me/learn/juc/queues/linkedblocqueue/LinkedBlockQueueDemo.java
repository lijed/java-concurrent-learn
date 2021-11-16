/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.linkedblocqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/11/13
 **/
public class LinkedBlockQueueDemo {
    LinkedBlockingQueue<String> taskPool = new LinkedBlockingQueue<>();

    {
        initialize();
    }

    private void initialize() {
        try {
            while (true) {
                String task = null;

                task = taskPool.take();

                System.out.println("executing task: " + task);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void addTask(String task) {
        taskPool.add(task);
    }


    public static void main(String[] args) {
        LinkedBlockQueueDemo demo = new LinkedBlockQueueDemo();
        demo.addTask("task1");
    }
}
