/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.forkjoin.demo.calculator;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/17
 **/
public class LoopCalculator implements Calculator {
    @Override
    public long sumUp(long[] numbers) {
        Long total = 0L;
        for (int i = 0; i < numbers.length; i++) {
            total += numbers[i];
        }
        return total;
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        LoopCalculator calculator= new LoopCalculator();
        Long result = calculator.sumUp(       LongStream.rangeClosed(0, 10000000L).toArray());
        Instant end = Instant.now();
        System.out.println("result = " + result +  ", total time spend" + Duration.between(start, end).toMillis());
    }
}
