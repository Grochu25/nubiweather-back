package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.models.Weather;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/realtime-weather")
@RequiredArgsConstructor
public class RealtimeWeatherController
{
    private final WeatherService weatherService;

    @GetMapping
    public List<Weather> getWeatherInGliwiceAndHamburg()
    {
        String[] cities = {"Gliwice","Hamburg"};
        return weatherService.getCurrentWeatherInCities(cities);
    }

    @GetMapping(path = "/{city}")
    public Weather getWeatherInCity(@PathVariable String city)
    {
        return weatherService.getCurrentWeatherInCity(city);
    }
}
