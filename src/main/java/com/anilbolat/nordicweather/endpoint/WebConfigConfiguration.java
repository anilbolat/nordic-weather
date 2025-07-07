package com.anilbolat.nordicweather.endpoint;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "web.config")
public class WebConfigConfiguration {
    
    private String frontendUrl;
    
}
