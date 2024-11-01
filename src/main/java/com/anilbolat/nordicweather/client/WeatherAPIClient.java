package com.anilbolat.nordicweather.client;

import com.anilbolat.nordicweather.service.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;


public class WeatherAPIClient implements WeatherService {

    private static final String URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" +
            "{location}/" +
            //"{date}/" +
            "{date}?" +
            "key={API_KEY}";
    private static final String API_KEY = "";

    @Override
    public String getWeather(String location, String date) {
        
        RestClient client = RestClient.create();

        return client.get()
                .uri(URL, location, date, API_KEY)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
    }
}
