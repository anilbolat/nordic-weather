package com.anilbolat.nordicweather.endpoint;

import com.anilbolat.nordicweather.service.WeatherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/weather")
@Slf4j
public class WeatherEndpoint {
    
    private final WeatherServiceImpl weatherService;

    public WeatherEndpoint(WeatherServiceImpl weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getWeather(@RequestParam String location, @RequestParam String date) {
        log.info("Getting weather for [{}, {}]", location, date);
        
        return this.weatherService.getWeather(location, date);
    }

}
