/*
 * Copyright 2021 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.me.learn.futuretask.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Administrator
 * Created: 2021/6/10
 **/
public class CompletableFutureConsumerTest {
    @Test
    public void testOneAccept() throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            return "Jedli";
        }).thenAccept(rs -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + rs);
        });
        System.out.println("cf = " + cf.get());
    }

    @Test
    public void testOneSyncAccept() throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            return "Jedli";
        }).thenAcceptAsync(
                rs -> {
                    printCurrentTheadName();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Hello " + rs);
                }
        );
        System.out.println("cf = " + cf.get());
    }

    @Test
    public void testThenAcceptBothAsync() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(5);
        }).thenAcceptBothAsync(CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            return Integer.valueOf(10);
        }), (rs1, rs2) -> {
            printCurrentTheadName();
            Integer total = rs1 + rs2;
            System.out.println(total);
        });


        System.out.println("do some processing");

        Object result = completableFuture.get();
        System.out.println(result);

    }

    @Test
    public void testThenAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(5);
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            return Integer.valueOf(10);
        }), (rs1, rs2) -> {
            printCurrentTheadName();
            Integer total = rs1 + rs2;
            System.out.println(total);
        });


        System.out.println("do some processing");

        Object result = completableFuture.get();
        System.out.println(result);

    }


    @Test
    public void testAcceptEither() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("RunAsync");
        }).acceptEither(CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("jed li");
        }), (rs) -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("consume the result...");
            System.out.println(rs);

        });

        try {
            completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5ThenAccepts() throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            printCurrentTheadName();
            return "Jedli";
        });
        CompletableFuture cf1 = cf.thenAcceptAsync(rs -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + rs);
        });
        CompletableFuture cf2 = cf.thenAcceptAsync(rs -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + rs);
        });
        CompletableFuture cf3 = cf.thenAccept(rs -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + rs);
        });
        CompletableFuture cf4 = cf.thenAccept(rs -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + rs);
        });
        CompletableFuture cf5 = cf.thenAccept(rs -> {
            printCurrentTheadName();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + rs);
        });
        System.out.println("cf = " + cf.get());
    }

    private void printCurrentTheadName() {
        System.out.println(Thread.currentThread().getName());
    }
}
