/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.container.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/27
 **/
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer > priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1; //倒序
            }
        });


        int j = 20;

        Random random = new Random();

        for (int i = 0; i < j; i++) {
            priorityQueue.add(random.nextInt(100));
        }
        System.out.println(priorityQueue.size());
        for (int i = 0; i < j; i++) {
            System.out.println("element = " + priorityQueue.poll());
        }
    }
}
