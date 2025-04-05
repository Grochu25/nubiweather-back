package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.ForecastedWeather;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/forecast-weather")
@RequiredArgsConstructor
public class ForecastWeatherController
{
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<List<ForecastedWeather>> getWeatherInGliwice()
    {
        String[] cities = {"Gliwice","Hamburg"};
        List<ForecastedWeather> forecastedWeathers = weatherService.getForecastedWeatherInCitiesForDays(cities, 7);
        if(forecastedWeathers == null || forecastedWeathers.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(weatherService.getForecastedWeatherInCitiesForDays(cities, 7), HttpStatus.OK);
    }

    @GetMapping(path = "/{city}")
    public ResponseEntity<ForecastedWeather> getWeatherInGliwice(@PathVariable String city)
    {
        ForecastedWeather forecastedWeather = weatherService.getForecastedWeatherInCityForDays(city, 7);
        if(forecastedWeather == null)
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(weatherService.getForecastedWeatherInCityForDays(city, 7), HttpStatus.OK);
    }
}
