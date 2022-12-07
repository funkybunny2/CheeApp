package com.example.fightingapp.models

import com.squareup.moshi.Json


data class WeatherData(
    @Json(name = "icon") val iconNmae: String,
)

data class CurrentConditionsData(
    @Json(name = "temp") val temperature: Float,
    @Json(name = "feels_like") val feelslike: Float,
    @Json(name = "temp_min") val minTemperature: Float,
    @Json(name = "temp_max") val maxTemperature: Float,
    @Json(name = "pressure") val pressure: Float,
    @Json(name = "humidity") val humidity: Float,
)

data class CurrentConditions(
    @Json(name = "name") val cityName: String,
    @Json(name = "weather") val weatherData: List<WeatherData>,
    @Json(name = "main") val conditions: CurrentConditionsData)
