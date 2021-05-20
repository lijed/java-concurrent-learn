/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.threadpool;

/**
 * @author Administrator
 * @date 2020/6/14 10:28
 * Project Name: java-concurrent-learn
 */
public class Test {
    private static final int COUNT_BITS = Integer.SIZE - 3;

//    1,1111, 1111 1111 ,1111 1111 ,1111 1111 前29位表示worker的数量
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    //1111111111111111111111111111111111100000, 0000 0000 ,0000 0000 ,0000 0000
    private static final int RUNNING    = -1 << COUNT_BITS;

    //0 0000, 0000 0000 0000 0000 0000 0000
    private static final int SHUTDOWN   =  0 << COUNT_BITS;

    //10 0000, 0000 0000 0000 0000 0000 0000
    private static final int STOP       =  1 << COUNT_BITS;
    //100 0000 ,0000 0000 ,0000 0000,0000 0000
    private static final int TIDYING    =  2 << COUNT_BITS;

    //110 0000 0000 0000 0000 0000 0000 0000
    private static final int TERMINATED =  3 << COUNT_BITS;


    public static void main(String[] args) {
        System.out.println("COUNT_BITS = " + COUNT_BITS);
        System.out.println("CAPACITY = " + CAPACITY);
        System.out.println("RUNNING = " + RUNNING);
        System.out.println("SHUTDOWN = " + SHUTDOWN);
        System.out.println("STOP = " + STOP);
        System.out.println("TIDYING = " + TIDYING);
        System.out.println("TERMINATED = " + TERMINATED);
        System.out.println(RUNNING | 0);
//        1110 0000 0000 0000 0000 0000 0000 0000
    }
}
