/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.threadlocale;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/31
 **/
public class ThreadLocalDemo {
    private static int num = 0;

    private static ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
//                num += 5;
//                System.out.println("num = " + num);
                int count = local.get();
                count += 5;
                local.set(count);

                System.out.println(Thread.currentThread().getName() +  " = " + local.get());
            });
        }

        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
    }
}
