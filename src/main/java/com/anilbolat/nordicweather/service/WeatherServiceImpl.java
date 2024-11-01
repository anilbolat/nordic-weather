package com.anilbolat.nordicweather.service;

import com.anilbolat.nordicweather.client.WeatherAPIClient;
import org.springframework.stereotype.Service;


@Service
public class WeatherServiceImpl implements WeatherService {
    
    private final WeatherAPIClient client;

    public WeatherServiceImpl(WeatherAPIClient client) {
        this.client = client;
    }

    @Override
    public String getWeather(String location, String date) {
        return this.client.getWeather(location, date);
    }
}
