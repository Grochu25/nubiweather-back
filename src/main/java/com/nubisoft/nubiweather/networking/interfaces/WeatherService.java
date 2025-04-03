package com.nubisoft.nubiweather.networking.interfaces;

import com.nubisoft.nubiweather.domain.ForecastedWeather;
import com.nubisoft.nubiweather.domain.Weather;

public interface WeatherService {
    public Weather getWeatherInCity(String city);
    public ForecastedWeather getForecastedWeatherInCityForDays(String city, Integer days);
}
