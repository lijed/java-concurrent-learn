/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.thread.visibility;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/19
 **/
public class ThreadDemo {
    private static boolean stop = false;

    public static class Worker extends  Thread {
        @Override
        public void run() {
            int i = 0;
            while (!stop) {
                i++;
            }

            //JIT 编译器深度优化有的代码
            //-Djava.complier=NONE 关闭JIT编译优化
            //优化后的代码
//            if(!stop) {
//                while(true) {
//                    i++;
//                }
//            }


        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Worker().start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;
    }

}
