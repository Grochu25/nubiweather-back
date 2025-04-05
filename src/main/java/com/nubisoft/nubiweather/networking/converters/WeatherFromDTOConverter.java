package com.nubisoft.nubiweather.networking.converters;

import com.nubisoft.nubiweather.models.Weather;
import com.nubisoft.nubiweather.networking.DTOs.WeatherDTO;

public class WeatherFromDTOConverter
{
    public static Weather convert(WeatherDTO dto)
    {
        return new Weather(
                null,
                dto.location().name(),
                dto.location().country(),
                dto.current().last_updated(),
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
