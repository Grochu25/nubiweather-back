package com.nubisoft.nubiweather.networking.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import static org.junit.jupiter.api.Assertions.*;

@RestClientTest(RestWeatherService.class)
class RestWeatherServiceTest
{
    @Autowired
    private RestWeatherService service;

    @Test
    public void responseIsNotNull()
    {
        var result = service.getWeatherInCity("Gliwice");

        assertNotNull(result);
    }
}