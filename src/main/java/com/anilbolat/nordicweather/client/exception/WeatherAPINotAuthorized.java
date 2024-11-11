package com.anilbolat.nordicweather.client.exception;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WeatherAPINotAuthorized extends RuntimeException {
    
    private final String message;
    
    public WeatherAPINotAuthorized(InputStream inputStream) {
        String msg;
        try {
            msg = convertStreamToString(inputStream);
        } catch (IOException e) {
            msg = e.getMessage();
        }
        message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    private static String convertStreamToString(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}
