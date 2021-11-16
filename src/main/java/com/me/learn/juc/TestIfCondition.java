/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.juc;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/11/14
 **/
public class TestIfCondition {
    public static void main(String[] args) {
        int a =  0, b = 0, c = 0;
        if ((a == b) || ( (c=6) > 5 ) ) {
            System.out.println("c=" + c);
        } else {

        }

    }
}
