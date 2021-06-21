/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.synchronization.waitnotify.demo;

import java.util.Queue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/14
 **/
public class Producer implements Runnable{

    private Queue<String> msg;
    private int maxSize;

    public Producer(Queue<String> msg, int maxSize) {
        this.msg = msg;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int i=0;
        while(true){
            i++;
            synchronized (msg){ //同一把锁.

                while(msg.size()==maxSize){
                    //如果生产满了
                    try {
                        msg.wait(); //一定会释放锁.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产者生产消息:"+i);
                msg.add("生产消息："+i);
                msg.notify(); //唤醒处于阻塞状态下的线程
//                msg.notifyAll();
            }
        }
    }
}