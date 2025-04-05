package com.nubisoft.nubiweather.data.interfaces;

import com.nubisoft.nubiweather.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Integer>
{
    Optional<Weather> getLastWeatherByCity(String city);
}
