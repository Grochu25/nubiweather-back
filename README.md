# NubiWeather Backend
Project created to apply for internship in nubisoft company. The application uses [weatherapi.com](https://www.weatherapi.com/) to retrieve current weather and a 7-day weather forecast for Gliwice and Hamburg.
## Project requirements
To run the project requires java 21 and docker to run database in container.

It also require to set the environemnet variable WEATHER_API_KEY with your api key for [weatherapi.com](https://www.weatherapi.com/).
## How to run project
After meeting the above requirements you can run the app in two stages:
 - First you have to run the compose file to set database conatiner. You can do this in the terminal, executing command 'docker compose up' from the project root direcotry.
 - Second you can run the spring project by your editor or by executing the command 'gradlew bootRun' in the terminal from the project root direcory.

The application runs on standard 8080 port.
## Endpoints
The application exposes 4 endpoints:
 - /realtime-weather - returns the current weather in Gliwice and Hamburg
 - /forecast-weather - returns the 7-day weather forecast for Gliwice and Hamburg.
 - /realtime-weather/Gliwice - returns the current weather in Gliwice
 - /realtime-weather/Hamburg - returns the current weather in Hamburg
 - /realtime-weather/Gliwice/last - returns the last recorded weather in Gliwice
 - /realtime-weather/Hamburg/last - returns the last recorded weather in Hamburg
## Database
The application uses MySQL database hosted in docker container. If you want to change the database, you can find the connection properties in the application.properties file. Data from the api is collected automatically by a scheduled task  once an hour.
