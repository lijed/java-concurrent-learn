/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.tools.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author Administrator
 * @date 2020/6/4 22:56
 * Project Name: java-concurrent-learn
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Car(semaphore, i).start();
        }
    }

    static class Car extends Thread {
        Semaphore semaphore;
        int num;

        public Car(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
        }


        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("第" + num + "个线程占用了一个令牌");
                Thread.sleep(3000);
                System.err.println("第" + num + "个线程释放了一个令牌");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
