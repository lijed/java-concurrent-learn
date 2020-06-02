/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.arrayblockingqueue.demo;

import lombok.Data;

/**
 * @author Administrator
 * @date 2020/6/1 22:57
 * Project Name: java-concurrent-learn
 */
@Data
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }
}
