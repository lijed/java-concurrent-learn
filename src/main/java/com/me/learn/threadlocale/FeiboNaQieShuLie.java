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
public class FeiboNaQieShuLie {
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        magicHash(16);
        magicHash(32);
    }

    private static void magicHash(int size) {
        int hashCode = 0;
        for (int i = 0; i < size; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            System.out.print((hashCode & (size - 1)) + " ");
        }
        System.out.println("");
    }
}
