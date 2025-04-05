package com.nubisoft.nubiweather.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nubisoft.nubiweather.models.weatherComponents.WeatherCondition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record Weather(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonIgnore
        Long id,
        String city,
        String country,
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
) {
}
