package com.nubisoft.nubiweather.data.interfaces;

import com.nubisoft.nubiweather.models.Weather;
import com.nubisoft.nubiweather.models.weatherComponents.WeatherCondition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WeatherRepositoryTest {
    @Autowired
    private WeatherRepository weatherRepository;
    private LocalDateTime mockDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

    @Test
    public void saveAndFindByTest(){
        Weather weather = new Weather(
                mockDate,"Test","Test",0.0f,0.0f, new WeatherCondition("Test","test"),0.0f,
                0.0f, "test", 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0.0f,
                0.0f,0.0f,0.0f,0.0f);
        weatherRepository.save(weather);

        Weather weather1 = weatherRepository.findByDataCollectTime(mockDate).orElse(null);
        Weather weather2 = weatherRepository.findLastWeatherByCity(weather.getCity()).orElse(null);

        assertNotNull(weather1);
        assertEquals(weather.getDataCollectTime(), weather1.getDataCollectTime());
        assertEquals(weather, weather1);

        assertNotNull(weather2);
        assertEquals(weather.getDataCollectTime(), weather2.getDataCollectTime());
        assertEquals(weather, weather2);
    }
}