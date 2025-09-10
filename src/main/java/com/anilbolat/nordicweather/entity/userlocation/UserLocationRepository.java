package com.anilbolat.nordicweather.entity.userlocation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    void deleteUserLocationByLocationAndDateAndUser_Id(String location, String date, Long userId);
}
