/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.synchronization;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/14
 **/
public class HeavyClassLockDemo {
    public static void main(String[] args) throws InterruptedException {
        HeavyClassLockDemo lockDemo = new HeavyClassLockDemo();
        Thread t1 = new Thread(()-> {
            synchronized (lockDemo) {
                System.out.println("t1 强制到锁");
                System.out.println(ClassLayout.parseInstance(lockDemo).toPrintable());
            }
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(10);
        Thread t2 = new Thread(()-> {
            synchronized (lockDemo) {
                System.out.println("t2 强制到锁");
                System.out.println(ClassLayout.parseInstance(lockDemo).toPrintable());
            }
        }, "t2");
        t2.start();
    }
}
