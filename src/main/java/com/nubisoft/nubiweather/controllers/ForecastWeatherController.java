package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.ForecastedWeather;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/forecast-weather")
public class ForecastWeatherController
{
    private final WeatherService weatherService;

    public ForecastWeatherController(WeatherService weatherService)
    {
        this.weatherService = weatherService;
    }

    @GetMapping
    public List<ForecastedWeather> getWeatherInGliwice()
    {
        String[] cities = {"Gliwice","Hamburg"};
        return weatherService.getForecastedWeatherInCitiesForDays(cities, 7);
    }
}
