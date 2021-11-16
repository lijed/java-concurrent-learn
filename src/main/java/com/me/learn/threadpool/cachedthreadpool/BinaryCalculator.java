/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.threadpool.cachedthreadpool;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/11/14
 **/
public class BinaryCalculator {
    private static final int COUNT_BITS =  Integer.SIZE - 3;

    public static void main(String[] args) {
        Integer running = -1 << COUNT_BITS;
        System.out.println(Integer.toBinaryString(running));

        System.out.println(Integer.toBinaryString(-1));
    }
}
