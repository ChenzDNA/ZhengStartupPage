package com.zheng.zhengstartuppage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author : 陈征
 * @date : 2021-12-20 08:36
 */

@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Scheduled(cron = "0 0 1 * * ?")
    public void addRedisToken() {
        Objects.requireNonNull(redisTemplate.keys("*")).forEach((key) -> {
            if (((Integer) redisTemplate.opsForValue().get(key) < 1)) {
                redisTemplate.opsForValue().set(key, 50);
            }
        });
    }
}
