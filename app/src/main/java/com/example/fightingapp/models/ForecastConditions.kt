package com.example.fightingapp.models

import com.squareup.moshi.Json

data class ForecastData(
    @Json(name = "dt") val date: Long,
    @Json(name ="sunrise") val sunrise: Long,
    @Json(name ="sunset") val sunset: Long,
    @Json(name = "temp") val temp: Temp,
)

data class Temp(
    @Json(name = "min") val min: Float,
    @Json(name = "max") val max: Float,
)

data class ForecastConditions(
    @Json(name = "list") val forecastData: List<ForecastData>,
    )
