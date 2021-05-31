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
public class Producer implements Runnable {
    private Queue<String> bags;
    private int maxSize;

    public Producer(Queue<String> bags, int maxSize) {
        this.bags = bags;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int i=0;
        while(true){
            i++;
            synchronized (bags){ //抢占锁
                if(bags.size()==maxSize){
                    System.out.println("bags 满了");
                    try {
//park(); ->JVM ->Native
                        bags.wait(); //满了，阻塞当前线程并且释放Producer抢到的锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产者生产：bag"+i);
                bags.add("bag"+i); //生产bag
                bags.notify(); //表示当前已经生产了数据，提示消费者可以消费了
            } //同步代码快执行结束
        }
    }
}
