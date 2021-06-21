package com.me.learn.thread.deadlock.resolve.demo2;

import com.me.learn.thread.deadlock.resolve.demo.Account;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 风骚的Mic 老师
 * create-date: 2020/5/23-20:08
 */
public class TransferAccount02 implements  Runnable{

    private Account fromAccount; //转出账户
    private Account toAccount; //转入账户
    private int amount;
    Lock fromLock=new ReentrantLock();
    Lock toLock=new ReentrantLock();

    public TransferAccount02(Account fromAccount, Account toAccount, int amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }


    @Override
    public void run() {
        while(true){
            if (fromLock.tryLock()) { //返回true和false
                try {
                    if (toLock.tryLock()) {//返回true和false
                        try {
                            if (fromAccount.getBalance() >= amount) {
                                fromAccount.debit(amount);
                                toAccount.credit(amount);
                            } else {
                                break;
                            }
                        } finally {
                            toLock.unlock();
                        }
                    }
                } finally {
                    fromLock.unlock();
                }

            }
            //转出账户的余额
            System.out.println(fromAccount.getAccountName() + "->" + fromAccount.getBalance());
            //转入账户的余额
            System.out.println(toAccount.getAccountName() + "->" + toAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        Account fromAccount=new Account("Mic",100000);
        Account toAccount=new Account("花花",300000);
        Thread a =new Thread(new TransferAccount02(fromAccount,toAccount,10));
        Thread b=new Thread(new TransferAccount02(toAccount,fromAccount,30));

        a.start();
        b.start();
    }
}
