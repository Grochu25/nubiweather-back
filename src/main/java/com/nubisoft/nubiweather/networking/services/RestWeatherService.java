package com.nubisoft.nubiweather.networking.services;

import com.nubisoft.nubiweather.models.ForecastedWeather;
import com.nubisoft.nubiweather.models.Weather;
import com.nubisoft.nubiweather.networking.DTOs.ForecastedWeatherDTO;
import com.nubisoft.nubiweather.networking.DTOs.LocationsBulkDTO;
import com.nubisoft.nubiweather.networking.DTOs.ResultWeatherApiBulkDTO;
import com.nubisoft.nubiweather.networking.DTOs.WeatherDTO;
import com.nubisoft.nubiweather.networking.converters.ForecastFromDTOConverter;
import com.nubisoft.nubiweather.networking.converters.WeatherFromDTOConverter;
import com.nubisoft.nubiweather.networking.errors.CustomResponseErrorHandler;
import com.nubisoft.nubiweather.networking.interfaces.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RestWeatherService implements WeatherService
{
    private final RestClient restClient;
    @Value("${WEATHER_API_KEY}")
    private String apiKey = "";

    public RestWeatherService()
    {
        restClient = RestClient.create();
    }

    @Override
    public Weather getCurrentWeatherInCity(String city) {
        try {
            WeatherDTO weatherDTO = restClient.get()
                    .uri("http://api.weatherapi.com/v1/current.json?key={apiKey}&q={city}&aqi=yes", apiKey, city)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(new CustomResponseErrorHandler())
                    .body(WeatherDTO.class);
            return (weatherDTO != null) ? WeatherFromDTOConverter.convert(weatherDTO) : null;
        } catch (Exception e){
            log.error("Couldn't get forecast for city {}: {}", city, e.getMessage());}

        return null;
    }

    @Override
    public ForecastedWeather getForecastedWeatherInCityForDays(String city, Integer days) {
        try {
            ForecastedWeatherDTO forecastDTO = restClient.get()
                    .uri("http://api.weatherapi.com/v1/forecast.json?key={apiKey}&q={city}&days={days}", apiKey, city, days)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(new CustomResponseErrorHandler())
                    .body(ForecastedWeatherDTO.class);
            return (forecastDTO != null) ? ForecastFromDTOConverter.convert(forecastDTO) : null;
        } catch (Exception e){
            log.error("Couldn't get forecast for city {}: {}", city, e.getMessage());}

        return null;
    }

    @Override
    public List<Weather> getCurrentWeatherInCities(String[] cities) {
        List<Weather> weather = new ArrayList<>();

        var weatherResultDTO = getCurrentWeatherBulkFromApi(cities);
        if(weatherResultDTO != null) {
            weather.addAll(convertFromWeatherDTO(weatherResultDTO));
        }

        return (weather.isEmpty()) ? null : weather;
    }

    private ResultWeatherApiBulkDTO<WeatherDTO> getCurrentWeatherBulkFromApi(String[] cities){
        try {
            return restClient.post()
                    .uri("http://api.weatherapi.com/v1/current.json?key={apiKey}&q=bulk", apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new LocationsBulkDTO(cities))
                    .retrieve()
                    .onStatus(new CustomResponseErrorHandler())
                    .toEntity(new ParameterizedTypeReference<ResultWeatherApiBulkDTO<WeatherDTO>>() {
                    }).getBody();
        } catch (Exception e){
            log.error("Couldn't get weather for multiple cities: {}", e.getMessage());}

        return null;
    }

    private List<Weather> convertFromWeatherDTO(ResultWeatherApiBulkDTO<WeatherDTO> dto){
        List<Weather> weather = new ArrayList<>();
        dto.bulk.forEach(bulk->{
            try{
                weather.add(WeatherFromDTOConverter.convert(bulk.query()));
            }catch (Exception e){
                log.error("converter error: "+e.getMessage());
            }
        });
        return (weather.isEmpty()||weather.contains(null)) ? new ArrayList<>() : weather;
    }

    @Override
    public List<ForecastedWeather> getForecastedWeatherInCitiesForDays(String[] cities, Integer days) {
        List<ForecastedWeather> weatherForecast = new ArrayList<>();

        var weatherForecastDTO = getForecastBulkFromApi(cities, days);
        if(weatherForecastDTO != null) {
            weatherForecast.addAll(convertFromForecastDTO(weatherForecastDTO));
        }

        return (weatherForecast.isEmpty()) ? null : weatherForecast;
    }

    private ResultWeatherApiBulkDTO<ForecastedWeatherDTO> getForecastBulkFromApi(String[] cities, Integer days){
        try {
            return restClient.post()
                    .uri("http://api.weatherapi.com/v1/forecast.json?key={apiKey}&q=bulk&days={days}", apiKey, days)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new LocationsBulkDTO(cities))
                    .retrieve()
                    .onStatus(new CustomResponseErrorHandler())
                    .toEntity(new ParameterizedTypeReference<ResultWeatherApiBulkDTO<ForecastedWeatherDTO>>() {
                    }).getBody();
        } catch (Exception e){
            log.error("Couldn't get forecast for multiple cities: {}", e.getMessage());}

        return null;
    }

    private List<ForecastedWeather> convertFromForecastDTO(ResultWeatherApiBulkDTO<ForecastedWeatherDTO> dto){
        List<ForecastedWeather> forecastedWeather = new ArrayList<>();
        dto.bulk.forEach(bulk->{
            try{
                forecastedWeather.add(ForecastFromDTOConverter.convert(bulk.query()));
            }catch (Exception e){
                log.error("converter error: "+e.getMessage());
            }
        });
        return (forecastedWeather.isEmpty()||forecastedWeather.contains(null)) ? new ArrayList<>() : forecastedWeather;
    }
}
