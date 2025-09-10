package com.anilbolat.nordicweather.weather;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {
    
    private final WeatherServiceImpl weatherService;

    public WeatherController(WeatherServiceImpl weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getWeather(@RequestParam String location, @RequestParam String date) {
        return this.weatherService.getWeather(location, date);
    }

}
