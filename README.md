# NubiWeather Backend
Project created to apply for internship in nubisoft company. Application uses [weatherapi.com](https://www.weatherapi.com/) to get current weather and weather forecast for 7 days in Gliwice and Hamburg.
## Project requirements
To run project requires java 21 and docker to run database in container.

It also require to set environemnet variable WEATHER_API_KEY with api key for [weatherapi.com](https://www.weatherapi.com/).
## How to run project
After meeting the above requirements you can run app in two stages:
 - First you have to run compose file to set database conatiner. You can do this in terminal, using command 'docker compose up' in project root direcotry.
 - Second you can run spring project by your editor or by running command 'gradlew bootRun' in terminal in project root direcory.

Application runs on standard 8080 port.
## Endpoints
Appication exposes 4 endopints:
 - /realtime-weather - return current weather in Gliwice and Hamburg
 - /forecast-weather - return forecast for next 7 days in Gliwice and Hamburg
 - /realtime-weather/Gliwice - return current weather in Gliwice
 - /realtime-weather/Hamburg - return current weather in Hamburg
 - /realtime-weather/Gliwice/last - return last registered weather in Gliwice
 - /realtime-weather/Hamburg/last - return last registered weather in Hamburg
## Database
Appication uses mysql database placed in docker container. If you want to change database, you can find connection properties in application.properties file.
