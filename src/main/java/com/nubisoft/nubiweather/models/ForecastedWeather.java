package com.nubisoft.nubiweather.models;

import com.nubisoft.nubiweather.models.weatherComponents.ForecastDay;
import com.nubisoft.nubiweather.models.weatherComponents.ForecastHour;

import java.util.List;

public record ForecastedWeather(
        String city,
        String country,
        List<Forecast> forecast
)
{
    public record Forecast(
            String date,
            ForecastDay day,
            List<ForecastHour> hours
        ){
        }
}
