package com.anilbolat.nordicweather.user;

import com.anilbolat.nordicweather.entity.user.User;
import com.anilbolat.nordicweather.entity.user.UserRepository;
import com.anilbolat.nordicweather.entity.userlocation.UserLocation;
import com.anilbolat.nordicweather.entity.userlocation.UserLocationRepository;
import com.anilbolat.nordicweather.weather.WeatherServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final int USER_LOCATIONS_LIMIT = 10;
    private final UserRepository userRepository;
    private final UserLocationRepository userLocationRepository;
    private final WeatherServiceImpl weatherService;


    @Override
    public List<UserController.ResponseUserWeather> getUserLocationsWeathers(String email) {
        log.info("Getting user locations weathers for [{}]", email);

        User u = findUserByEmailOrThrow(email);

        List<UserController.ResponseUserWeather> weatherList = new ArrayList<>();
        u.getUserLocations().forEach(userLocation -> {
            String location = userLocation.getLocation();
            String date = String.valueOf(userLocation.getDate());
            String weather = this.weatherService.getWeather(location, date);

            weatherList.add(new UserController.ResponseUserWeather(location, date, weather));
        });


        return weatherList;
    }

    @Transactional
    @Override
    public boolean saveUserLocation(String email, String location, String date) {
        log.info("Saving user location for [{}, {}, {}]", email, location, date);

        User u = findUserByEmailOrThrow(email);

        UserLocation userLocation = UserLocation.builder()
                .location(location)
                .date(date)
                .user(u)
                .build();

        if (!u.getUserLocations().contains(userLocation)) {
            deleteFirstUserLocation(u);
            userLocationRepository.save(userLocation);
            return true;
        } else {
            log.warn("Already exist in user locations, [{}, {}, {}]", location, date, u);
        }

        return false;
    }

    @Transactional
    @Override
    public void deleteUserLocation(String email, String location, String date) {
        log.info("Deleting user location for [{}, {}, {}]", email, location, date);

        User u = findUserByEmailOrThrow(email);

        userLocationRepository.deleteUserLocationByLocationAndDateAndUser_Id(location, date, u.getId());
    }

    private User findUserByEmailOrThrow(String email) {
        log.info("Finding user for [{}]", email);

        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private void deleteFirstUserLocation(User u) {
        log.info("Deleting first user location for [{}]", u.getEmail());

        if (u.getUserLocations().size() == USER_LOCATIONS_LIMIT) {
            log.warn("User locations are full. [{}]", u);
            try {
                userLocationRepository.delete(u.getUserLocations().get(0));
            } catch (Exception e) {
                var msg = String.format("Failed to delete user location [%s]", e.getMessage());
                throw new UserLocationDeleteException(msg, e);
            }
        }
    }

}
