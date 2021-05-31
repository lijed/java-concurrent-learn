/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.lock.condition.demo1.condition;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/26
 **/
public class ConditionBlockedQueue {

    private List<String> items;

    // 已经添加的元素个数
    private volatile int size;
    //list 的size
    private volatile int count;

    private final Lock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public ConditionBlockedQueue(int count) {
        this.items = new ArrayList<>(count);
        this.count = count;
    }

    public boolean add(String element) throws InterruptedException {
        lock.lock();
        try {
            while (items.size() >= count) {
                System.out.println("队列满了，先休息一会 " + Thread.currentThread().getName());
                notFull.await();
            }
            ++size;
            items.add(element);
            System.out.println("生产了：" + element);
            Thread.sleep(1000);
            notEmpty.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return true;
    }

    public String take() {
        lock.lock();
        try {
            while (size == 0) {
                System.out.println("队列为空了，先休息一会 " +  Thread.currentThread().getName());
                notEmpty.await();
            }
            --size;
            Thread.sleep(2000);
            String result = items.remove(0);
            System.out.println("消费了：" + result);
            notFull.signalAll();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    @SneakyThrows
    public static void main(String[] args) {
        ConditionBlockedQueue conditionBlockedQueue = new ConditionBlockedQueue(10);
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    conditionBlockedQueue.add("task" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        Thread consumer = new Thread(() -> {
            while (true) {
                String task = conditionBlockedQueue.take();

            }
        });
        consumer.start();

    }

}
