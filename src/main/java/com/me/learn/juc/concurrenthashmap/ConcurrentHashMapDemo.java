/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2020/6/7 10:35
 * Project Name: java-concurrent-learn
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) {

        System.out.println(tableSizeFor(33));
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap();
        hashMap.put("k1", "jed");
        System.out.println("key1 ->  " + hashMap.get("k1"));

        //32795: 0000 0000 0000 0000 1000000000011011
        //       1000000000011011 (扩容标记)   00000000 00000000
        //       1000000000011011 (扩容标记)   00000000 00000010  （+2） 参与扩容的线程数
        // 高位表示当前长度唯一的扩容标记
        // 地位表示协助扩容的线程数量
        System.out.println(Integer.numberOfLeadingZeros(16));
        System.out.println((1 << 15));
        System.out.println(Integer.numberOfLeadingZeros(16) | (1 << (16 - 1)));
        int rs = Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));
        System.out.println((rs << 16) + 2);

        Integer sizeCtl = (rs << 16) + 2;
        System.out.println(Integer.toBinaryString(sizeCtl));

        System.out.println("==========");
        System.out.println(Integer.numberOfLeadingZeros(32));
        System.out.println((1 << 15));
        System.out.println("rs=" + (Integer.numberOfLeadingZeros(32) | (1 << (16 - 1))));
        rs = Integer.numberOfLeadingZeros(32) | (1 << (16 - 1));
        System.out.println("rs的二进制:" + Integer.toBinaryString(rs));
        System.out.println((rs << 16) + 2);

         sizeCtl = (rs << 16) + 2;
        System.out.println(Integer.toBinaryString(sizeCtl));


    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }
}
