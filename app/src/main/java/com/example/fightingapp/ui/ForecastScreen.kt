package com.example.fightingapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fightingapp.ForecastTemp
import com.example.fightingapp.R
import com.example.fightingapp.models.DayForecast
import com.example.fightingapp.models.ForecastConditions
import com.example.fightingapp.models.ForecastData
import com.example.fightingapp.toHourMinute
import com.example.fightingapp.toMonthDay

var forecastItem: List<DayForecast> = listOf()



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForecastScreen(viewModel: ForecastViewModel = hiltViewModel()){
    val state by viewModel.forecastConditions.collectAsState(null )
    LaunchedEffect(Unit){
        viewModel.fetchData()
    }


    state?.let { forecastItem = forecastData(it) }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Forecast") }
        )
    }
    ){
        LazyColumn{
            items(items = forecastItem){ item: DayForecast ->
                ForecastRow(item = item)
            }
        }
    }
}
wq:wq

private fun forecastData(forecastConditions : ForecastConditions): List<DayForecast> {
    val forecastData = (0 until 16).map {
        DayForecast(
            date = forecastConditions.forecastData[0].date + (it * (24*60*60)),
            sunrise = forecastConditions.forecastData[0].sunrise + (it * (24*60*60)),
            sunset = forecastConditions.forecastData[0].sunset + (it * (24*60*60)),
            temp = ForecastTemp(day = 80f,min = forecastConditions.forecastData[0].temp.min+ it, max = forecastConditions.forecastData[0].temp.max+it),
            pressure = 1024f,
            humidity = 76,
        )
    }
    return forecastData
}


@Composable
private fun ForecastRow(item: DayForecast){
Row(

    modifier = Modifier.background(Color.White),
    verticalAlignment = Alignment.CenterVertically,
) {
    val textStyle = TextStyle(
        fontSize = 12.sp
    )
    Image(painter = painterResource(id = R.drawable.sun_icon), contentDescription = "")
    Spacer(modifier = Modifier.weight(1f,fill=true))
    Text(text = item.date.toMonthDay(),
        fontSize = 16.sp)
    Spacer(modifier = Modifier.weight(1f,fill=true))
    Column {
        Text(text = stringResource(id = R.string.high, item.temp.max.toInt()),
        style= textStyle)
        Text(text = stringResource(id = R.string.low, item.temp.min.toInt()),
            style= textStyle)
    }
    Spacer(modifier = Modifier.weight(1f,fill=true))
    Column {
        Text(text = stringResource(id = R.string.sunrise, item.sunrise.toHourMinute()),
            style= textStyle)
        Text(text = stringResource(id = R.string.sunset, item.sunset.toHourMinute()),
            style= textStyle)
    }
    }
}






