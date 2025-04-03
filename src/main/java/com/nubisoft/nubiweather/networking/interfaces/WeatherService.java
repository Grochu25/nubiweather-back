package com.nubisoft.nubiweather.networking.interfaces;

import com.nubisoft.nubiweather.models.ForecastedWeather;
import com.nubisoft.nubiweather.models.Weather;

import java.util.List;

public interface WeatherService {
    Weather getWeatherInCity(String city);
    List<Weather> getWeatherInCities(String[] cities);

    ForecastedWeather getForecastedWeatherInCityForDays(String city, Integer days);
    List<ForecastedWeather> getForecastedWeatherInCitiesForDays(String[] cities, Integer days);
}
