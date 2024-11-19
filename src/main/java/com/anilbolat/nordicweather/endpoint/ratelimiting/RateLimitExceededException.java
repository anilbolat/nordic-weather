package com.anilbolat.nordicweather.endpoint.ratelimiting;

public class RateLimitExceededException extends RuntimeException {
    
    public RateLimitExceededException(String message) {
        super(message);
    }
    
}
