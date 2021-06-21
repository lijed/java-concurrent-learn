/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.forkjoin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/17
 **/
public class ForkJoinTaskTest {


    /***
     * ForkJoinTask 可以直接调用
     */
    @Test
    public void testForkJoinTaskInvoke() {
        ForkJoinTask forkJoinTask = new RecursiveAction() {
            @Override
            protected void compute() {
                System.out.println("computing");
            }
        };

        forkJoinTask.invoke();
    }
}
