/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/2
 **/
public class ConcurrentHashMapApiDemo {
    public static final ConcurrentHashMap<String, Integer> USER_ACCESS_COUNT = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        Integer count = USER_ACCESS_COUNT.get("jed");
        if (count == null)  {
            USER_ACCESS_COUNT.put("jed", 1);
        }  else {
            USER_ACCESS_COUNT.put("jed", count + 1);
        }


        USER_ACCESS_COUNT.computeIfAbsent("jed", (key) -> {return 1;});
        USER_ACCESS_COUNT.computeIfPresent("jed", (key, value) -> {
            System.out.println("value = " + value);return value + 1;});

        System.out.println(USER_ACCESS_COUNT.get("jed"));

        ConcurrentHashMap<Integer, Integer> integerConcurrentHashMap = new ConcurrentHashMap<>();
        Stream.of(1,2,3,4,1,4,2).forEach(v-> {
            integerConcurrentHashMap.merge(v, 3, Integer::sum);
        });
        System.out.println(integerConcurrentHashMap);

//        * V oldValue = map.get(key);
//     * V newValue = (oldValue == null) ? value :
//     *              remappingFunction.apply(oldValue, value);
//     * if (newValue == null)
//     *     map.remove(key);
//     * else
//     *     map.put(key, newValue);
//     * }
    }
}
