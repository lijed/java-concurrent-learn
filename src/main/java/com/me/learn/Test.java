/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/4
 **/
public class Test {
    public static void main(String[] args) {
        int a =1;
        int i= ++a;
        System.out.println("i = " + i);
        System.out.println("a = " + a);


        i = a++;
        System.out.println("i = " + i);
        System.out.println("a = " + a);

    }
}
