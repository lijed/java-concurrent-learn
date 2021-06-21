/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.lock.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/24
 **/
public class ReentrantReadWriteLockDemo {

    static Map<String,Object> cacheMap=new HashMap<>();
    //读读不互斥
    //写写是互斥的
    //读写是互斥的
    static ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    static Lock read=rwl.readLock();
    static Lock write=rwl.writeLock();

    public static Object get(String key){
        read.lock(); //读锁 ThreadA 阻塞
        try{
            System.out.println(Thread.currentThread().getName() + " from time" + System.currentTimeMillis());
            if (Thread.currentThread().getName().equals("thread0")) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return cacheMap.get(key);
        }finally {
            read.unlock(); //释放读锁
        }
    }
    public static Object write(String key,Object value){
        write.lock(); //Other Thread 获得了写锁
        try{
            return cacheMap.put(key,value);
        }finally {
            write.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo.write("name", "jed");

        int  i = 100;
        for (int i1 = 0; i1 < i; i1++) {
            new Thread(()->{
                Object result = ReentrantReadWriteLockDemo.get("name");
                System.out.println(Thread.currentThread().getName() + " get result: " + result);
            }, "thread" + i1).start();
        }

    }
}
