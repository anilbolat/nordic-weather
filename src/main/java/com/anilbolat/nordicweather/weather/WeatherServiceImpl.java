package com.anilbolat.nordicweather.weather;

import com.anilbolat.nordicweather.weather.cache.CacheService;
import com.anilbolat.nordicweather.weather.client.WeatherAPIClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
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
        log.info("Getting weather for [{}, {}]", location, date);

        String weather = this.cacheService.getFromCache(location, date);
        if (weather == null) {
            log.info("Not found in cache, [{}, {}]", location, date);
            weather = this.weatherAPIClient.getWeather(location, date);
            this.cacheService.saveToCache(location, date, weather);
        }
        
        return weather;
    }
}
