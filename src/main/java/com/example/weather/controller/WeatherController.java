package com.example.weather.controller;

import com.example.weather.model.Weather;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherRepository repository;

    @GetMapping
    public Iterable<Weather> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{latitude}/{longitude}")
    public Optional<Weather> findByLatLon(@PathVariable double latitude, @PathVariable double longitude){
        return repository.findByLatitudeAndLongitude(latitude, longitude);
    }

    @GetMapping("/{id}")
    public Optional<Weather> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Weather> save(@RequestBody Weather weather) {
        if (weather.getDate() == null) {
            weather.setDate(new Date());
        }
        return repository.findById(weather.getId()).isPresent()
                ? new ResponseEntity(repository.findById(weather.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(repository.save(weather), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Weather> optionalObject = repository.findById(id);
        if (optionalObject.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
