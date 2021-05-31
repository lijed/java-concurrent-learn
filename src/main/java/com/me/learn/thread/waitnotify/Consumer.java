/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.thread.waitnotify;

import java.util.Queue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/25
 **/
public class Consumer implements  Runnable{
    private Queue<String> bags;
    private int maxSize;
    public Consumer(Queue<String> bags, int maxSize) {
        this.bags = bags;
        this.maxSize = maxSize;
    } @
            Override
    public void run() {
        while(true){
            synchronized (bags){
                if(bags.isEmpty()){
                    System.out.println("bags为空");
                    try {
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String bag=bags.remove();
                System.out.println("消费者消费："+bag);
                bags.notify();
                //这里只是唤醒Producer线程，但是Producer线程并不能马上
                //执行。join join也是基于wait/notify来实现，notify是在线程销毁之后调用的，代码如下。
            } //同步代码块执行结束， monitorexit指令执行完成
        }
    }
}
