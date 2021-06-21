/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.forkjoin.demo.calculator;

import javax.management.InstanceNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/17
 **/
public class LongStreamDemo {
    public static void main(String[] args) {
        Instant start = Instant.now();
        long result = LongStream.rangeClosed(0, 10000000L).parallel().reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
        System.out.println("结果为：" + result); // 打印结果500500
    }
}
