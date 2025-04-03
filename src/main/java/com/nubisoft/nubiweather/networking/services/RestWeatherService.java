package com.nubisoft.nubiweather.networking.services;

import com.nubisoft.nubiweather.domain.Weather;
import com.nubisoft.nubiweather.networking.errors.CustomResponseErrorHandler;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestWeatherService implements WeatherService
{
    private RestClient restClient;
    private String apiKey = ""; //TODO place your api key here

    public RestWeatherService()
    {
        restClient = RestClient.create();
    }

    @Override
    public Weather getWeatherInCity(String city) {
        return restClient.get()
                .uri("http://api.weatherapi.com/v1/current.json?key={apiKey}&q={city}&aqi=yes", apiKey, city)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(new CustomResponseErrorHandler())
                .body(Weather.class);
    }
}
