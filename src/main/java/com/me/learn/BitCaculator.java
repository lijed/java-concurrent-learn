/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn;

/**
 * @author Administrator
 * @date 2020/6/14 23:36
 * Project Name: java-concurrent-learn
 */
public class BitCaculator {
    public static void main(String[] args) {
        int num = -1;
        System.out.println("-1 = " + Integer.toBinaryString(num));
        // step1. -1的绝对值表示： 00000000 00000000 00000000 00000001
        //step2.  取反：  11111111 11111111 11111111  11111110
        //Step3。  加1： 11111111 11111111 11111111  11111111

        System.out.println("-1 >>> 1: " + (-1 >>> 1));

        //#1；先把num向有整体移动了一位；
        //(移动后被后移的高位为空)111 1111 1111 1111 1111 1111 1111 1111 1(超出了32位被丢弃)
        //#2；之后在空高位补0
        System.out.println("-1 >>> 1: " + Integer.toBinaryString(-1 >>> 1));  //0111 1111 1111 1111 1111 1111 1111 1111

        //有符号右移， 高位被移动端的位置补1
        System.out.println("-1 >> 1: " + (-1 >> 1)); // -1
        System.out.println("-1 >> 1: " + Integer.toBinaryString((-1 >> 1))); //1111 1111 1111 1111 1111 1111 1111 1111


        //有符号左移， 低位被移动端的位置补0， 相当于乘以2

        System.out.println("1 << 1: " + (1 << 2)); // 4
        System.out.println("1 << 1: " + Integer.toBinaryString((1 << 2))); //00000000 00000000 00000000 00000100
    }
}
