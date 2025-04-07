package com.nubisoft.nubiweather.data.interfaces;

import com.nubisoft.nubiweather.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Integer>
{
    Optional<Weather> findLastWeatherByCity(String city);
    Optional<List<Weather>> findByDataCollectTime(LocalDateTime last_updated);
}
