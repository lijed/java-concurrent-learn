/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.thread.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/25
 **/
public class App {
    public static void main(String[] args) {
        Queue bags = new LinkedList();
        int maxSize = 10;

        Thread producer = new Thread(new Producer(bags, maxSize));
        Thread consumer = new Thread(new Consumer(bags, maxSize));
        producer.start();
        consumer.start();


    }
}
