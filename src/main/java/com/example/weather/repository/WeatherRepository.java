package com.example.weather.repository;

import com.example.weather.model.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Integer> {
    Optional<Weather> findByLatitudeAndLongitude(double latitude, double longitude);
}
