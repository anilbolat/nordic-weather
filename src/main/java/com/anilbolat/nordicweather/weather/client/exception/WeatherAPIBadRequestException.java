package com.anilbolat.nordicweather.weather.client.exception;


public class WeatherAPIBadRequestException extends RuntimeException {
    
    private final String message;
    
    public WeatherAPIBadRequestException(String responseBody) {
        this.message = responseBody;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
