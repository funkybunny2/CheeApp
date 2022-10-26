package com.example.fightingapp.models

import com.example.fightingapp.ForecastTemp

data class DayForecast(
    val date: Long, val sunrise: Long,
    val sunset: Long, val temp: ForecastTemp,
    val pressure: Float, val humidity: Int
)

data class ForcastTemp(val min: Float,
                       val max: Float
                       )
