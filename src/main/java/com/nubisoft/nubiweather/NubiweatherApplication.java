package com.nubisoft.nubiweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class NubiweatherApplication {
	public static void main(String[] args) {
		String apiKey = System.getenv("WEATHER_API_KEY");
		if (apiKey == null) {
			System.err.println("Error: Weather API Key environment variable not set");
		}
		else
			SpringApplication.run(NubiweatherApplication.class, args);
	}
}
