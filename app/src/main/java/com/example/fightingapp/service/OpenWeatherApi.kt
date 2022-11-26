package com.example.fightingapp.service

import com.example.fightingapp.models.CurrentConditions
import com.example.fightingapp.models.ForecastConditions
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentCondidtions(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "61e80a81c053d9a475382b912d5f20d9",
        @Query("units") units: String = "imperial"
    ): CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecastConditions(
        @Query("zip") zip: String,
        @Query("appid") appid: String = "e22b4a86b5fad1d599b91bc54a3576ee",
        @Query("units") units: String = "imperial",
        @Query("cnt") cnt: String = "16"
    ): ForecastConditions
}