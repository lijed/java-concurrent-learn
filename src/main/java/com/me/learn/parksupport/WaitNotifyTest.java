/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.parksupport;

/**
 * @author Administrator
 * @date 2020/6/2 23:34
 * Project Name: java-concurrent-learn
 */
public class WaitNotifyTest {
    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new WaitThread()).start();
        new Thread(new NotifyThread()).start();
    }

    static class WaitThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("start wait!");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end wait!");
            }
        }
    }


    static class NotifyThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("Start notify.");
                lock.notify();
                System.out.println("End Nodify.");
            }
        }
    }
}
