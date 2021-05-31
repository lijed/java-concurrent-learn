/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.lock.condition.demo1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/24
 **/
public class App {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread waitThread = new Thread(new ConditionWait(lock, condition), "ConditionWait");
        Thread signalThread = new Thread(new ConditionSignal(lock, condition), "ConditionSignal");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        signalThread.start();
    }
}
