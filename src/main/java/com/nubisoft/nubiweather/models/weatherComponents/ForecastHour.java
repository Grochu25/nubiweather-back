package com.nubisoft.nubiweather.models.weatherComponents;

public record ForecastHour(
        String time,
        Float temp_c,
        Float temp_f,
        WeatherCondition condition,
        Float wind_mph,
        Float wind_kph,
        String wind_dir,
        Float pressure_mb,
        Float pressure_in,
        Float precip_mm,
        Float precip_in,
        Float snow_cm,
        Integer humidity,
        Integer cloud,
        Float feelslike_c,
        Float feelslike_f,
        Integer will_it_rain,
        Integer chance_of_rain,
        Integer will_it_snow,
        Integer chance_of_snow,
        Float vis_km,
        Float vis_miles,
        Integer uv
){}
