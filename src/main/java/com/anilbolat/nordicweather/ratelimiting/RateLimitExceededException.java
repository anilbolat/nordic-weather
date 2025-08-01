package com.anilbolat.nordicweather.ratelimiting;

public class RateLimitExceededException extends RuntimeException {
    
    public RateLimitExceededException(String message) {
        super(message);
    }
    
}
