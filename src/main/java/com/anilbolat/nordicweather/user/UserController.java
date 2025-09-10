package com.anilbolat.nordicweather.user;

import com.anilbolat.nordicweather.security.config.userdetails.CustomUserDetails;
import com.anilbolat.nordicweather.weather.WeatherServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    
    private UserService userService;
    private WeatherServiceImpl weatherService;

    @GetMapping("/locations/weather")
    public List<ResponseUserWeather> getLocationsWeather(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return this.userService.getUserLocationsWeathers(userDetails.getUsername());
    }

    @PostMapping("/locations/weather")
    public String addUserWeather(@RequestBody RequestUserWeather requestUserWeather, @AuthenticationPrincipal CustomUserDetails userDetails) {
        boolean saved = this.userService.saveUserLocation(userDetails.getUsername(), requestUserWeather.location, requestUserWeather.date);

        if (saved) {
            return this.weatherService.getWeather(requestUserWeather.location, requestUserWeather.date);
        }

        return "Already exists.";
    }

    @DeleteMapping("/locations/weather")
    public ResponseEntity<String> deleteUserWeather(@RequestParam String location,
                                            @RequestParam String date,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        this.userService.deleteUserLocation(userDetails.getUsername(), location, date);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public record RequestUserWeather(String location,
                                     String date) {
    }

    public record ResponseUserWeather(String location,
                                      String date,
                                      String weather) {
    }
    
}
