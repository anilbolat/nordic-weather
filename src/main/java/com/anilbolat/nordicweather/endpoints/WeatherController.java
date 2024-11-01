package com.anilbolat.nordicweather.endpoints;

import com.anilbolat.nordicweather.service.WeatherService;
import com.anilbolat.nordicweather.service.WeatherServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    @GetMapping
    public String getWeather(@RequestParam String location, @RequestParam String date) {
        WeatherService weatherService = new WeatherServiceImpl();
        return weatherService.getWeather(location, date);
    }

}
