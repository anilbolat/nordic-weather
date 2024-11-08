package com.anilbolat.nordicweather.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CacheService {
    
    private final RedisTemplate<String, String> redisTemplate;
    
    @Value("${redis.expiration.timeout}")
    private long expirationTimeout;
    
    public CacheService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    public void saveToCache(String location, String date, String value) {
        this.redisTemplate.opsForValue().set(constructKey(location, date), value, Duration.ofMinutes(expirationTimeout));
    }
    
    public String getFromCache(String location, String date) {
        return this.redisTemplate.opsForValue().get(constructKey(location, date));
    }
    
    public void deleteFromCache(String location, String date) {
        this.redisTemplate.delete(constructKey(location, date));
    }
    
    public String constructKey(String location, String date) {
        return location + ":" + date;
    }
    
}
