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

public class Consumer implements Runnable {
    private Queue<String> msg;
    private int maxSize;

    public Consumer(Queue<String> msg, int maxSize) {
        this.msg = msg;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while(true){
            synchronized (msg){
                while(msg.isEmpty()){
                    //如果消息队列为空了
                    try {
                        msg.wait(); //阻塞当前线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费消息："+msg.remove());
                msg.notify(); //唤醒处于阻塞状态下的生产者
            }
        }
    }
}
