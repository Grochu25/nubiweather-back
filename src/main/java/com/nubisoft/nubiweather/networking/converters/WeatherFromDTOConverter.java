package com.nubisoft.nubiweather.networking.converters;

import com.nubisoft.nubiweather.models.Weather;
import com.nubisoft.nubiweather.networking.DTOs.WeatherDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherFromDTOConverter
{
    public static Weather convert(WeatherDTO dto) throws Exception
    {
        return new Weather(
                null,
                LocalDateTime.parse(dto.current().last_updated(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                dto.location().name(),
                dto.location().country(),
                dto.current().temp_c(),
                dto.current().temp_f(),
                dto.current().condition(),
                dto.current().wind_mph(),
                dto.current().wind_kph(),
                dto.current().wind_dir(),
                dto.current().pressure_mb(),
                dto.current().pressure_in(),
                dto.current().precip_mm(),
                dto.current().precip_in(),
                dto.current().humidity(),
                dto.current().cloud(),
                dto.current().feelslike_c(),
                dto.current().feelslike_f(),
                dto.current().vis_km(),
                dto.current().vis_miles(),
                dto.current().uv()
        );
    }
}
