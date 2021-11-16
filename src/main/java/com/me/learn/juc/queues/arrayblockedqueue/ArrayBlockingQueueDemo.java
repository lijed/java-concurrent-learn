/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.arrayblockedqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/27
 **/
public class ArrayBlockingQueueDemo {

    ArrayBlockingQueue<String> arrayBlockQueue = new ArrayBlockingQueue<String>(50);
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    {
        init();
    }

    private void init() {

        Runnable runnable = () -> {
            while (true) {
                try {
                    String task = arrayBlockQueue.take();
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " is executing task: " + task);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        executorService.submit(runnable);
        executorService.submit(runnable);
    }


    public void addTask(String task) {
        boolean isAdded = arrayBlockQueue.offer(task);
        if (isAdded) {
            System.out.println("task: " + task + " is added into queure");
        } else {
            System.out.println("Executing task: " + task + " in add task thread, because queue is full.");
        }
    }

    public static void main(String[] args) {
        ArrayBlockingQueueDemo demo = new ArrayBlockingQueueDemo();
        for (int i = 0; i < 20; i++) {
            demo.addTask("task " + i);
        }
    }

}
