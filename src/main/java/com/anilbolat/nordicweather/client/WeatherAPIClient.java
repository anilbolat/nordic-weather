package com.anilbolat.nordicweather.client;

import com.anilbolat.nordicweather.service.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
public class WeatherAPIClient implements WeatherService {

    private static final String URL_PATH_TEMPLATE = "{location}/" +
            //"{date}/" +
            "{date}?" +
            "key={API_KEY}";
    
    private final WeatherAPIConfiguration configuration;
    
    public WeatherAPIClient(WeatherAPIConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getWeather(String location, String date) {
        
        RestClient client = RestClient.create();

        return client.get()
                .uri(this.configuration.getUrl() + URL_PATH_TEMPLATE, location, date, configuration.getKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
    }
}
