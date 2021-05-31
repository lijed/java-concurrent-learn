/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.lock.condition.demo1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/24
 **/
public class ConditionWait implements Runnable {
    Lock lock;
    Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " start to wait");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " is waken up and stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
