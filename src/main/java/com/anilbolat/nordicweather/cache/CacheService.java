package com.anilbolat.nordicweather.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
public class CacheService {
    
    private final RedisTemplate<String, String> redisTemplate;
    
    @Value("${redis.expiration.timeout.minutes}")
    private long expirationTimeout;
    
    public CacheService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    public void saveToCache(String location, String date, String value) {
        log.info("Saving weather {} to cache for [{}, {}]", value, location, date);
        try {
            this.redisTemplate.opsForValue().set(constructKey(location, date), value, Duration.ofMinutes(expirationTimeout));
        } catch (RuntimeException ex) {
            var msg = String.format("Cannot save weather %s to cache for [%s, %s]", value, location, date);
            throw new CacheNotAvailableException(msg, ex);
        }
    }

    public String getFromCache(String location, String date) {
        log.info("Getting weather from cache [{}, {}]", location, date);
        try {
            return this.redisTemplate.opsForValue().get(constructKey(location, date));
        } catch (RuntimeException ex) {
            var msg = String.format("Cannot get weather from cache for [%s, %s]", location, date);
            throw new CacheNotAvailableException(msg, ex);
        }
    }
    
    private static String constructKey(String location, String date) {
        return location + ":" + date;
    }
    
}
