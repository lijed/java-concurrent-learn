/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.thread.lock;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/23
 **/
public class SynchronizedLockDemo {
    private static volatile SynchronizedLockDemo instance;

    public static SynchronizedLockDemo getInstance() {
        if (instance == null) {
            synchronized (SynchronizedLockDemo.class) {
                if (instance == null) {
                    instance = new SynchronizedLockDemo();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }

}
