/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.threadlocale;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/30
 **/
public class SimpleDateFormatDemo {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    private static final ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<DateFormat>() {
//
//        @Override
//        protected DateFormat initialValue() {
//            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        }
//    };
    private static final ThreadLocal<DateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(()-> {return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");});

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 30; i++) {
            executorService.submit(() -> {
                try {
//                    System.out.println(dateFormat.parse("2021-05-30 20:20:20"));
                    System.out.println(dateFormatThreadLocal.get().parse("2021-05-30 20:20:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
