package com.anilbolat.nordicweather.endpoint;

import com.anilbolat.nordicweather.endpoint.ratelimiting.RateLimitingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RateLimitingInterceptor rateLimitingInterceptor;
    
     public WebConfig(RateLimitingInterceptor rateLimitingInterceptor) {
         this.rateLimitingInterceptor = rateLimitingInterceptor;
     }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitingInterceptor);
    }
}
