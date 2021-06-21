/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.concurrent.hashmap;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/5
 **/
public class ChmSearchFunctionDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        int totalElements = 100;
        final Random random = new Random();
        for (int i = 0; i < totalElements; i++) {
            concurrentHashMap.put("key" + i, random.nextInt(10000));
        }

        System.out.println("size: "  + concurrentHashMap.size());
        Integer result = concurrentHashMap.searchValues(10, value -> {
            System.out.println("value = " + value);
            if (value /2 == 0) {
                return value * 2;
            }  else {
                return value;
            }
        });
        System.out.println("result = " + result);
    }
}

