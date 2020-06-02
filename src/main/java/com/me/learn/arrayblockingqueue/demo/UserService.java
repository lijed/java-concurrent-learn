/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.arrayblockingqueue.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @date 2020/6/1 22:58
 * Project Name: java-concurrent-learn
 */
public class UserService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    ArrayBlockingQueue<User> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    {
        init();
    }

    public void init() {
        executorService.execute(() -> {
            while(true) {
                try {
                    User user = arrayBlockingQueue.take();
                    System.out.println("发送优惠券");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public boolean register() {
        User user = new User("Jed");
        addUser(user);
        try {
            arrayBlockingQueue.put(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void addUser(User user) {
        System.out.println("添加用户"+user);
    }

    public static void main(String[] args) {
        new UserService().register();
    }
}
