package com.anilbolat.nordicweather.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "weather.api")
public class WeatherAPIConfiguration {
    private final String DEFAULT_KEY = "your_weather_api_key";
    
    private String key;
    private String url;
}
