package com.example.redisson.controller;

import org.redisson.api.RAtomicLong;
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
}
