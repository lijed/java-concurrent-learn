/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.synchronization;

import org.openjdk.jol.info.ClassLayout;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/14
 **/
public class ClassLayoutDemo {
    public static void main(String[] args) {
        ClassLayoutDemo classLayoutDemo=new ClassLayoutDemo();
        synchronized (classLayoutDemo) {
            System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
        }
    }

    public void printNoLock() {
        ClassLayoutDemo classLayoutDemo=new ClassLayoutDemo();
        System.out.println(ClassLayout.parseInstance(classLayoutDemo).toPrintable());
    }
}
