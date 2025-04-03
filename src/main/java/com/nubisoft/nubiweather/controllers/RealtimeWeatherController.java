package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.domain.Weather;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/realtime-weather")
public class RealtimeWeatherController
{
    private final WeatherService weatherService;

    public RealtimeWeatherController(WeatherService weatherService)
    {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Weather getWeatherInGliwice()
    {
        return weatherService.getWeatherInCity("Gliwice");
    }
}
