package com.example.fightingapp.ui


import androidx.lifecycle.ViewModel
import com.example.fightingapp.models.CurrentConditions
import com.example.fightingapp.service.OpenWeatherApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor(private val api: OpenWeatherApi): ViewModel(){
    private val _currentConditions = Channel<CurrentConditions> {  }
    public val currentConditions: Flow<CurrentConditions> = _currentConditions.receiveAsFlow()

    fun fetchData() = runBlocking {
        val currentConditions = api.getCurrentCondidtions("55411")
        _currentConditions.trySend(currentConditions)
    }
}