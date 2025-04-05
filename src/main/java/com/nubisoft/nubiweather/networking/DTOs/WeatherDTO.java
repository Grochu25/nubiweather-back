package com.nubisoft.nubiweather.networking.DTOs;

import com.nubisoft.nubiweather.models.weatherComponents.WeatherCondition;

public record WeatherDTO(
        Location location,
        Current current
) {
    public record Location(String name, String country) {}
    public record Current(
            String last_updated,
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
            Integer humidity,
            Integer cloud,
            Float feelslike_c,
            Float feelslike_f,
            Float vis_km,
            Float vis_miles,
            Float uv
    ){}
}
