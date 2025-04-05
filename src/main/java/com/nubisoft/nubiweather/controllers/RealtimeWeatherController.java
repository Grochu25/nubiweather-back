package com.nubisoft.nubiweather.controllers;

import com.nubisoft.nubiweather.data.interfaces.WeatherRepository;
import com.nubisoft.nubiweather.models.Weather;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/realtime-weather")
@RequiredArgsConstructor
public class RealtimeWeatherController
{
    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;
    private final String[] cities = {"Gliwice","Hamburg"};

    @GetMapping
    public ResponseEntity<List<Weather>> getWeatherInGliwiceAndHamburg()
    {
        List<Weather> weathers = weatherService.getCurrentWeatherInCities(cities);
        if(weathers != null) {
            for (Weather weather : weathers) {
                saveToDataBaseIfHourElapsed(weather);
            }
            return new ResponseEntity<>(weathers, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping(path = "/last")
    public ResponseEntity<List<Weather>> getLastRegisteredWeatherInGliwiceAndHamburg()
    {
        List<Weather> weathers = new ArrayList<>();
        for(String city : cities)
            weathers.add(weatherRepository.getLastWeatherByCity(city).orElse(null));

        if(!weathers.isEmpty())
            return new ResponseEntity<>(weathers, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping(path = "/{city}")
    public ResponseEntity<Weather> getWeatherInCity(@PathVariable String city)
    {
        Weather weather = weatherService.getCurrentWeatherInCity(city);
        if(weather != null){
            saveToDataBaseIfHourElapsed(weather);
            return new ResponseEntity<>(weather, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping(path = "/{city}/last")
    public ResponseEntity<Weather> getLastRegisteredWeatherInCity(@PathVariable String city)
    {
        Weather weather = weatherRepository.getLastWeatherByCity(city).orElse(null);
        if(weather != null)
            return new ResponseEntity<>(weather, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private void saveToDataBaseIfHourElapsed(Weather weather)
    {
        weatherRepository.getLastWeatherByCity(weather.getCity()).ifPresentOrElse(
                lastWeather -> {
                    if(Duration.between(lastWeather.getLast_updated(), weather.getLast_updated()).toHours() >= 1)
                        weatherRepository.save(weather);},
                ()->weatherRepository.save(weather)
        );
    }
}
