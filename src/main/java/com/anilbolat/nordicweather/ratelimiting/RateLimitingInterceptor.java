package com.anilbolat.nordicweather.ratelimiting;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BandwidthBuilder;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {
    
    private final Bucket bucket;

    public RateLimitingInterceptor(RateLimitingConfiguration rateLimitingConfiguration) {
        Bandwidth bandwidth = BandwidthBuilder.builder()
                .capacity(rateLimitingConfiguration.getCapacity())
                .refillIntervally(rateLimitingConfiguration.getToken(), Duration.ofMinutes(rateLimitingConfiguration.getPeriodMinutes()))
                .build();
        this.bucket = Bucket.builder()
                .addLimit(bandwidth)
                .build();
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (this.bucket.tryConsume(1)) {
            return true;
        } else {
            throw new RateLimitExceededException("Too many requests!");
        }
    }
}
