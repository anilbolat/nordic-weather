package com.anilbolat.nordicweather.client.exception;

public class WeatherAPINotAvailable extends RuntimeException {

    private final String message;
    
    public WeatherAPINotAvailable(String responseBody) {
        this.message = responseBody;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
