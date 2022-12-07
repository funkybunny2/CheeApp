package com.example.fightingapp.ui

import androidx.lifecycle.ViewModel
import com.example.fightingapp.models.ForecastConditions
import com.example.fightingapp.models.LatitudeLongitude
import com.example.fightingapp.service.OpenWeatherApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val api: OpenWeatherApi): ViewModel(){
    private val _forecastConditions = Channel<ForecastConditions> {  }
    public val forecastConditions: Flow<ForecastConditions> = _forecastConditions.receiveAsFlow()

    fun fetchData() = runBlocking {
        val forecastConditions = api.getForecastConditions("55411")
        _forecastConditions.trySend(forecastConditions)
    }

    fun fetchCurrentLocationData(latitudeLongitude: LatitudeLongitude) = runBlocking {
        val forecastConditions = api.getForecastConditions(latitudeLongitude.latitude,latitudeLongitude.longitude)
        _forecastConditions.trySend(forecastConditions)
    }
}