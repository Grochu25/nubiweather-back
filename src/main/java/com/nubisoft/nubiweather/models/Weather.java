package com.nubisoft.nubiweather.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nubisoft.nubiweather.models.weatherComponents.WeatherCondition;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force=true)
@AllArgsConstructor
@Data
public class Weather{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonIgnore
        private Long id;
        private final LocalDateTime dataCollectTime;
        private final String city;
        private final String country;
        private final Float temp_c;
        private final Float temp_f;
        private final WeatherCondition condition;
        private final Float wind_mph;
        private final Float wind_kph;
        private final String wind_dir;
        private final Float pressure_mb;
        private final Float pressure_in;
        private final Float precip_mm;
        private final Float precip_in;
        private final Integer humidity;
        private final Integer cloud;
        private final Float feelslike_c;
        private final Float feelslike_f;
        private final Float vis_km;
        private final Float vis_miles;
        private final Float uv;
}
