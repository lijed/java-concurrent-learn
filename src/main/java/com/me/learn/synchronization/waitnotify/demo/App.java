/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.synchronization.waitnotify.demo;

import com.me.learn.thread.waitnotify.Consumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/14
 **/
public class App {
    public static void main(String[] args) {
        Queue<String> queue=new LinkedList<>();
        int maxSize=5;
        Producer product=new Producer(queue,maxSize);
        Consumer consumer=new Consumer(queue,maxSize);
        Thread pThread=new Thread(product);
        Thread cThread=new Thread(consumer);
        pThread.start();
        cThread.start();
    }
}
