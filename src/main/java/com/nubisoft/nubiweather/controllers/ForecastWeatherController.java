package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.domain.ForecastedWeather;
import com.nubisoft.nubiweather.domain.Weather;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ForecastedWeather getWeatherInGliwice()
    {
        return weatherService.getForecastedWeatherInCityForDays("Gliwice", 7);
    }
}
