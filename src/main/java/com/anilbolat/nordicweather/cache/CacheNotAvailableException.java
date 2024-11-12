package com.anilbolat.nordicweather.cache;

public class CacheNotAvailableException extends RuntimeException {
    
    public CacheNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
