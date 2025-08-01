package com.anilbolat.nordicweather.ratelimiting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rate.limiting")
public class RateLimitingConfiguration {
    
    private int capacity;
    private int token;
    private int periodMinutes;
    
}
