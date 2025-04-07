package com.nubisoft.nubiweather.data.collectors;

import com.nubisoft.nubiweather.data.interfaces.WeatherRepository;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ScheduledWeatherSave {
    private final WeatherRepository weatherRepository;
    private final WeatherService weatherService;

    @Scheduled(fixedRate = 60, timeUnit = TimeUnit.MINUTES)
    public void saveHistoricWeather() {
        String[] cities = new String[] {"Gliwice", "Hamburg"};
        var weathers = weatherService.getCurrentWeatherInCities(cities);
        if(weathers != null) {
            weatherRepository.saveAll(weathers);
        }
    }
}
