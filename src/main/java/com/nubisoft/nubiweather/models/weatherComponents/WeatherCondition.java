package com.nubisoft.nubiweather.models.weatherComponents;

import jakarta.persistence.Embeddable;

@Embeddable
public record WeatherCondition(String text, String icon){}
