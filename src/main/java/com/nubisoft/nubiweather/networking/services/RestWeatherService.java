package com.nubisoft.nubiweather.networking.services;

import com.nubisoft.nubiweather.models.ForecastedWeather;
import com.nubisoft.nubiweather.models.Weather;
import com.nubisoft.nubiweather.networking.DTOs.LocationsBulkDTO;
import com.nubisoft.nubiweather.networking.DTOs.ResultWeatherApiBulkDTO;
import com.nubisoft.nubiweather.networking.errors.CustomResponseErrorHandler;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestWeatherService implements WeatherService
{
    private RestClient restClient;
    @Value("${WEATHER_API_KEY}")
    private String apiKey = "";

    public RestWeatherService()
    {
        restClient = RestClient.create();
    }

    @Override
    public Weather getWeatherInCity(String city) {
        Weather weather = null;
        try {
            weather = restClient.get()
                    .uri("http://api.weatherapi.com/v1/current.json?key={apiKey}&q={city}&aqi=yes", apiKey, city)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(new CustomResponseErrorHandler())
                    .body(Weather.class);
        }catch (Exception e) {e.printStackTrace();}

        return weather;
    }

    @Override
    public ForecastedWeather getForecastedWeatherInCityForDays(String city, Integer days) {
        ForecastedWeather forecastedWeather = null;
        try {
            forecastedWeather = restClient.get()
                    .uri("http://api.weatherapi.com/v1/forecast.json?key={apiKey}&q={city}&days={days}", apiKey, city, days)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(new CustomResponseErrorHandler())
                    .body(ForecastedWeather.class);
        }catch (Exception e) {e.printStackTrace();}

        return forecastedWeather;
    }

    @Override
    public List<Weather> getWeatherInCities(String[] cities) {
        List<Weather> weather = new ArrayList<>();
        try {
            var weatherResultDTO = restClient.post()
                    .uri("http://api.weatherapi.com/v1/current.json?key={apiKey}&q=bulk", apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new LocationsBulkDTO(cities))
                    .retrieve()
                    .onStatus(new CustomResponseErrorHandler())
                    .toEntity(new ParameterizedTypeReference<ResultWeatherApiBulkDTO<Weather>>() {
                    }).getBody();

            if(weatherResultDTO != null) {
                weather.addAll(weatherResultDTO.bulk.stream().map((ResultWeatherApiBulkDTO.Bulk::query)).toList());
            }
        }catch(Exception e) {e.printStackTrace();}

        return (weather.isEmpty()) ? null : weather;
    }

    @Override
    public List<ForecastedWeather> getForecastedWeatherInCitiesForDays(String[] cities, Integer days) {
        List<ForecastedWeather> weatherForecast = new ArrayList<>();
        try {
            var weatherForecastDTO = restClient.post()
                    .uri("http://api.weatherapi.com/v1/forecast.json?key={apiKey}&q=bulk&days={days}", apiKey, days)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new LocationsBulkDTO(cities))
                    .retrieve()
                    .onStatus(new CustomResponseErrorHandler())
                    .toEntity(new ParameterizedTypeReference<ResultWeatherApiBulkDTO<ForecastedWeather>>() {
                    }).getBody();
            if(weatherForecastDTO != null) {
                weatherForecast.addAll(weatherForecastDTO.bulk.stream().map((ResultWeatherApiBulkDTO.Bulk::query)).toList());
            }
        }catch (Exception e) {e.printStackTrace();}

        return (weatherForecast.isEmpty()) ? null : weatherForecast;
    }
}
