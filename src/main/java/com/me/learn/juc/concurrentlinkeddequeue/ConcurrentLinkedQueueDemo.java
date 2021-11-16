/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.concurrentlinkeddequeue;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/11/5
 **/
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();

        concurrentLinkedDeque.add("task1");
        concurrentLinkedDeque.addFirst("task2");
        concurrentLinkedDeque.addLast("tasklast");
        concurrentLinkedDeque.addLast("tasklast2");
        concurrentLinkedDeque.forEach( string -> System.out.println(string));
    }
}
