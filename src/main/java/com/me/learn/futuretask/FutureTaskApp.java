/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask;

import java.util.concurrent.*;

/**
 *
 *
 * Callable/Futuretask的原理猜想
 * @author Administrator
 * @date 2020/6/13 21:52
 * Project Name: java-concurrent-learn
 */
public class FutureTaskApp {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "Jed Li";
            }
        });

        new Thread(futureTask).start();

        String result = null;
        try {
            try {
                result = futureTask.get(1000, TimeUnit.NANOSECONDS);
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + result);
    }
}
