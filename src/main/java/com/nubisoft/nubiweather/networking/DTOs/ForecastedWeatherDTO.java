package com.nubisoft.nubiweather.networking.DTOs;

import com.nubisoft.nubiweather.models.weatherComponents.WeatherCondition;
import com.nubisoft.nubiweather.models.weatherComponents.ForecastHour;

import java.util.List;

public record ForecastedWeatherDTO(
        Location location,
        Forecast forecast
)
{
    public record Location(String name, String country) {}
    public record Forecast(
            List<ForecastDay> forecastday
    ){
        public record ForecastDay(
                String date,
                com.nubisoft.nubiweather.models.weatherComponents.ForecastDay day,
                List<ForecastHour> hour
        ){
            public record Day(
                    Float maxtemp_c,
                    Float maxtemp_f,
                    Float mintemp_c,
                    Float mintemp_f,
                    Float avgtemp_c,
                    Float avgtemp_f,
                    Float maxwind_mph,
                    Float maxwind_kph,
                    Float totalprecip_mm,
                    Float totalprecip_in,
                    Float totalsnow_cm,
                    Float avgvis_km,
                    Float avgvis_miles,
                    Integer avghumidity,
                    Integer daily_will_it_rain,
                    Integer daily_chance_of_rain,
                    Integer daily_will_it_snow,
                    Integer daily_chance_of_snow,
                    WeatherCondition condition,
                    Float uv
            ){ }
            public record Hour(
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
                    Float windchill_c,
                    Float windchill_f,
                    Float heatindex_c,
                    Float heatindex_f,
                    Float dewpoint_c,
                    Float dewpoint_f,
                    Integer will_it_rain,
                    Integer chance_of_rain,
                    Integer will_it_snow,
                    Integer chance_of_snow,
                    Float vis_km,
                    Float vis_miles,
                    Integer uv
            ){
            }
        }
    }
}
