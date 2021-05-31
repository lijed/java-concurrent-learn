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
public class BitCalculation {
    public static void main(String[] args) {
       int n=20;
       int i = 10;
       int length = 16;
        do {
            i = nextIndex(i, length);
            System.out.println("i = " + i);
        } while ( (n >>>= 1) != 0);
    }

    private static int nextIndex(int i, int len) {
        return ((i + 1 < len) ? i + 1 : 0);
    }

    /**
     * Decrement i modulo len.
     */
    private static int prevIndex(int i, int len) {
        return ((i - 1 >= 0) ? i - 1 : len - 1);
    }

}
