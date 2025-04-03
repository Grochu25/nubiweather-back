package com.nubisoft.nubiweather.networking.interfaces;

import com.nubisoft.nubiweather.domain.Weather;

public interface WeatherService {
    public Weather getWeatherInCity(String city);
}
