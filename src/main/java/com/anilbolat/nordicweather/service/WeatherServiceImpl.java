package com.anilbolat.nordicweather.service;

import com.anilbolat.nordicweather.cache.CacheService;
import com.anilbolat.nordicweather.client.WeatherAPIClient;
import org.springframework.stereotype.Service;


@Service
public class WeatherServiceImpl implements WeatherService {
    
    private final WeatherAPIClient weatherAPIClient;
    private final CacheService cacheService;

    public WeatherServiceImpl(WeatherAPIClient weatherAPIClient, CacheService cacheService) {
        this.weatherAPIClient = weatherAPIClient;
        this.cacheService = cacheService;
    }

    @Override
    public String getWeather(String location, String date) {
        String weather = this.cacheService.getFromCache(location, date);
        if (weather == null) {
            weather = this.weatherAPIClient.getWeather(location, date);
            this.cacheService.saveToCache(location, date, weather);
        }
        
        return weather;
    }
}
