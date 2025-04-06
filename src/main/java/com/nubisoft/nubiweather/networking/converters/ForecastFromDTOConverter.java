package com.nubisoft.nubiweather.networking.converters;

import com.nubisoft.nubiweather.models.ForecastedWeather;
import com.nubisoft.nubiweather.networking.DTOs.ForecastedWeatherDTO;

public class ForecastFromDTOConverter
{
    public static ForecastedWeather convert(ForecastedWeatherDTO dto) throws Exception
    {
        return new ForecastedWeather(
                dto.location().name(),
                dto.location().country(),
                dto.forecast().forecastday().stream().map(
                        forecastDay -> new ForecastedWeather.Forecast(
                                forecastDay.date(),
                                forecastDay.day(),
                                forecastDay.hour()
                                )
                ).toList()
        );
    }
}
