package com.me.learn.futuretask.completablefuture.demo;

import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class RepoService {
    public Integer getRepoByGoodsId(Integer goodsId){
        return new Random().nextInt(1000);
    }
}
