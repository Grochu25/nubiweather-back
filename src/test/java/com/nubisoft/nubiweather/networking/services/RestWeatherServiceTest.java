package com.nubisoft.nubiweather.networking.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestClientTest(RestWeatherService.class)
class RestWeatherServiceTest
{
    @Autowired
    private RestWeatherService service;


    @Test
    public void apiKeyEnvironmentVariableIsNotNull()
    {
        assertNotNull(System.getenv().get("WEATHER_API_KEY"));
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "WEATHER_API_KEY", matches="null")
    public void weatherForOneCityIsNotNull()
    {

        var result = service.getCurrentWeatherInCity("Gliwice");

        assertNotNull(result);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "WEATHER_API_KEY", matches="null")
    public void weatherForOneCityShouldBeNull()
    {
        var result1 = service.getCurrentWeatherInCity("");
        var result2 = service.getCurrentWeatherInCity(null);

        assertNull(result1);
        assertNull(result2);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "WEATHER_API_KEY", matches="null")
    public void weatherForManyCitiesIsNotNull()
    {
        String[] cities = new String[]{"Gliwice","Hamburg"};
        var result = service.getCurrentWeatherInCities(cities);

        assertNotNull(result);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "WEATHER_API_KEY", matches="null")
    public void weatherForManyCitiesShouldBeNullWithBadArguments()
    {
        String[] cities = new String[0];
        var result1 = service.getCurrentWeatherInCities(cities);
        var result2 = service.getCurrentWeatherInCities(null);
        cities = new String[]{""};
        var result3 = service.getCurrentWeatherInCities(cities);

        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "WEATHER_API_KEY", matches="null")
    public void forecastForOneCityIsNotNull()
    {

        var result = service.getForecastedWeatherInCityForDays("Gliwice",7);

        assertNotNull(result);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "WEATHER_API_KEY", matches="null")
    public void forecastForOneCityShouldBeNullWithBadArguments()
    {
        var result1 = service.getForecastedWeatherInCityForDays("", 7);
        var result2 = service.getForecastedWeatherInCityForDays(null, 7);

        assertNull(result1);
        assertNull(result2);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "WEATHER_API_KEY", matches="null")
    public void forecastForManyCitiesIsNotNull()
    {
        String[] cities = new String[]{"Gliwice","Hamburg"};
        var result = service.getForecastedWeatherInCitiesForDays(cities,7);

        assertNotNull(result);
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "WEATHER_API_KEY", matches="null")
    public void forecastForManyCitiesShouldBeNullWithBadArguments()
    {
        String[] cities = new String[0];
        var result1 = service.getForecastedWeatherInCitiesForDays(cities, 7);
        var result2 = service.getForecastedWeatherInCitiesForDays(null, 7);
        cities = new String[]{""};
        var result3 = service.getForecastedWeatherInCitiesForDays(cities, 7);

        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
    }
}