package com.nubisoft.nubiweather.networking.converter;

import com.nubisoft.nubiweather.networking.DTOs.ForecastedWeatherDTO;
import com.nubisoft.nubiweather.networking.DTOs.WeatherDTO;
import com.nubisoft.nubiweather.networking.converters.ForecastFromDTOConverter;
import com.nubisoft.nubiweather.networking.converters.WeatherFromDTOConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConvertersTest {
    @Test
    public void convertersShouldThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            ForecastFromDTOConverter.convert(null);
        });
        assertThrows(NullPointerException.class, () -> {
            ForecastFromDTOConverter.convert(new ForecastedWeatherDTO(null, null));
        });

        assertThrows(NullPointerException.class, () -> {
            WeatherFromDTOConverter.convert(null);
        });
        assertThrows(NullPointerException.class, () -> {
            WeatherFromDTOConverter.convert(new WeatherDTO(null, null));
        });
    }
}
