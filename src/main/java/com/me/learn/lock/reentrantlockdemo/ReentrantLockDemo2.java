/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.lock.reentrantlockdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/24
 **/
public class ReentrantLockDemo2 {
    private static int count=0;

    //重入锁(如何实现的？)
    static Lock lock=new ReentrantLock(true);

    public static void inc(){
        lock.lock(); //获得锁(互斥锁) ThreadA 获得了锁
        try {
            //退出线程 中断的过程往下传递.  true
            // sleep/ join/ wait
            //while()
            // ...
            Thread.sleep(1);
            count++;
            decr();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁 ThreadA释放锁  state=1-1=0
        }
    }
    public static void decr(){
        lock.lock(); //state=2   //ThreadA再次来抢占锁 : 不需要再次抢占锁，而是只增加重入的次数
        try{
            count--;
        }finally {
            lock.unlock(); //state=1
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->ReentrantLockDemo2.inc());
        t1.start();


        Thread t2=new Thread(()->ReentrantLockDemo2.inc());
        t2.start();
        t2.interrupt(); //中断线程。




        for (int i = 0; i < 1000; i++) {

            new Thread(()->ReentrantLockDemo2.inc()).start();
        }
        Thread.sleep(4000);
        System.out.println("result:"+count);
    }
}
