/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.thread.visibility;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/5/19
 **/
public class VolatileDemo {
    public static volatile  boolean stop = false;

    public static void modify() {
        stop = true;
    }

    public static void main(String[] args) {
        modify();
    }
}
