package com.hlw.springboot_study.redis_message;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: houlongwang
 * description:
 * created_time: 2020/8/26 11:58
 * updated_time: 2020/8/26 11:58
 * updated_desc:
 * updated_by: houlongwang
 * version: 1.0.0
 */
@Slf4j
public class Receiver {
    private AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message) {
        log.info("Received <{}>",message);
        log.info("Received <{}>",counter.get());
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
