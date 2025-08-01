package com.anilbolat.nordicweather.webconfig;

import com.anilbolat.nordicweather.ratelimiting.RateLimitingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RateLimitingInterceptor rateLimitingInterceptor;
    private final WebConfigConfiguration webConfigConfiguration;
    
     public WebConfig(RateLimitingInterceptor rateLimitingInterceptor, WebConfigConfiguration webConfigConfiguration) {
         this.rateLimitingInterceptor = rateLimitingInterceptor;
         this.webConfigConfiguration = webConfigConfiguration;
     }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitingInterceptor);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(webConfigConfiguration.getFrontendUrl())
                        .allowedMethods("*");
            }
        };
    }

}
