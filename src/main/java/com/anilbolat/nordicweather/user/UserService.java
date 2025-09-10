package com.anilbolat.nordicweather.user;

import java.util.List;

public interface UserService {

    List<UserController.ResponseUserWeather> getUserLocationsWeathers(String email);

    boolean saveUserLocation(String email, String location, String date);

    void deleteUserLocation(String email, String location, String date);
}
