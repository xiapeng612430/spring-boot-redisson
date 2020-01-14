package com.example.redisson.controller;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 夏先鹏
 * @date 2020/01/06
 * @time 19:46
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/RAtomicLong")
    public void RAtomicLong() {
        RAtomicLong myLong = redissonClient.getAtomicLong("myLong");
        // 同步执行方式
        myLong.compareAndSet(3, 401);

    }

    @GetMapping("/RZset")
    public void rzet() {
        RScoredSortedSet<String> myZset = redissonClient.getScoredSortedSet("myZset");
        myZset.add(100, "t1");
        myZset.add(200, "t2");
        myZset.add(300, "t3");
        myZset.add(400, "t4");
        myZset.add(500, "t5");
        myZset.add(600, "t6");
        myZset.add(700, "t7");
        myZset.add(800, "t8");
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                String s = myZset.pollFirst();
                System.out.println(s);

            }).start();
        }
    }
}
