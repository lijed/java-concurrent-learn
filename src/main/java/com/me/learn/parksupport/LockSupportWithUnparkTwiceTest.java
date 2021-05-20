/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.parksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Administrator
 * @date 2020/6/5 8:52
 * Project Name: java-concurrent-learn
 */
public class LockSupportWithUnparkTwiceTest {

    public static void main(String[] args) {
        Thread parkThread = new Thread(new ParkThread());
        parkThread.start();
        System.out.println("开始线程唤醒");
        LockSupport.unpark(parkThread);
        LockSupport.unpark(parkThread); //连续调用两个Unpark，最多只有一个许可可以使用
//        new Thread(()-> {
//            LockSupport.unpark(parkThread);
//        }).start();
        System.out.println("结束线程唤醒");
    }

    static  class ParkThread implements Runnable {
        @Override
        public void run() {
            System.out.println("开始线程阻塞");
            LockSupport.park();
            LockSupport.park();
            System.out.println("结束线程阻塞");
        }
    }
}
