package com.nubisoft.nubiweather.models;

public record ForecastedWeather(
        Location location,
        Forecast forecast
)
{
    public record Location(String name, String country) {}
    public record Forecast(
            ForecastDay[] forecastday
    ){
        public record ForecastDay(
            String date,
            Day day
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
                Condition condition,
                Float uv
            ){
                public record Condition(String text, String icon){}
            }
        }
    }
}
