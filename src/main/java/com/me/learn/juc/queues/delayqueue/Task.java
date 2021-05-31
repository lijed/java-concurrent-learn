/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc.queues.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/27
 **/
public class Task implements Delayed {

    private Long availableTime;
    private Long delaySeconds;
    private String name;

    public Task(long delaySeconds, String name) {
        this.availableTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(delaySeconds);
        this.name = name;
    }

    public Long getAvailableTime() {
        return availableTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diffTime = availableTime - System.currentTimeMillis();
        return unit.convert(diffTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
       return (int)(this.availableTime - ((Task)o).getAvailableTime());
    }


    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}
