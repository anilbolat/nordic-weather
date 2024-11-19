package com.anilbolat.nordicweather.client;

import com.anilbolat.nordicweather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Slf4j
@Component
public class WeatherAPIClient implements WeatherService {

    private static final String URL_PATH_TEMPLATE = "{location}/" +
            "{date}?" +
            "key={API_KEY}";
    
    private final WeatherAPIConfiguration configuration;
    
    public WeatherAPIClient(WeatherAPIConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getWeather(String location, String date) {
        log.info("Fetching weather using weather api [{}, {}]", location, date);
        
        RestClient client = RestClient.builder()
                .baseUrl(this.configuration.getUrl())
                .build();

        return client.get()
                .uri(URL_PATH_TEMPLATE, location, date, configuration.getKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(new WeatherAPIResponseErrorHandler())
                .body(String.class);
    }
}
