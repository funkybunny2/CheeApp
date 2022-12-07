package com.example.fightingapp.ui

import android.annotation.SuppressLint
import android.media.Image
import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.fightingapp.R
import com.example.fightingapp.models.CurrentConditions
import com.example.fightingapp.models.LatitudeLongitude
import java.time.format.TextStyle


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentConditionsScreen(
    latitudeLongitude: LatitudeLongitude?,
    viewModel: CurrentConditionsViewModel = hiltViewModel(),
    onWeatherForMyLocationButton: () -> Unit,
                            onForecastButtonClick: () -> Unit,
)
{
    val state by viewModel.currentConditions.collectAsState(null )
        if(latitudeLongitude != null){
        LaunchedEffect(Unit){
            viewModel.fetchCurrentLocationData(latitudeLongitude)
            }
        }else{
            LaunchedEffect(Unit){
                viewModel.fetchData()
            }
    }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }
        )
    }
    ){
        state?.let {
            CurrentConditionsContent(it, onWeatherForMyLocationButton,onForecastButtonClick)
        }
    }
}

@Composable
private fun CurrentConditionsContent(
                                     currentConditions: CurrentConditions,
                                     onWeatherForMyLocationButton: () -> Unit,
                                     onForecastButtonClick: () -> Unit,){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = currentConditions.cityName,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(400)
            )
        )
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column {
                Text(text = stringResource(id = R.string.current_temp, R.string.current_temp),
                    fontSize = 72.sp, fontWeight = FontWeight(600),
                    textAlign = TextAlign.Start
                )
                Text(text = stringResource(id = R.string.feels_like, currentConditions.conditions.feelslike.toInt()),fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.weight(1f, fill = true))
            androidx.compose.foundation.Image(
                modifier = Modifier.size(72.dp),
                painter = painterResource(id = R.drawable.sun_icon),
                contentDescription = "Sunny")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = stringResource(id = R.string.low, currentConditions.conditions.minTemperature.toInt()),fontSize = 18.sp
            )
            Text(
                text = stringResource(id = R.string.high, currentConditions.conditions.maxTemperature.toInt()),fontSize = 18.sp
            )
            Text(
                text = stringResource(id = R.string.humidity, currentConditions.conditions.humidity.toInt()),fontSize = 18.sp
            )
            Text(text = stringResource(id = R.string.pressure, currentConditions.conditions.pressure.toInt()),fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(72.dp))
        Button(onClick = onForecastButtonClick) {
            Text(text = stringResource(id = R.string.forecast))
        }
        Spacer(modifier = Modifier.height(72.dp))
        Button(onClick = {onWeatherForMyLocationButton()}){
            Text(text = stringResource(id = R.string.get_weather_for_my_location))
        }
    }
}

