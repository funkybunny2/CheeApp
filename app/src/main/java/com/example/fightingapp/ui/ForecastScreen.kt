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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fightingapp.R
import com.example.fightingapp.models.ForecastData
import com.example.fightingapp.models.LatitudeLongitude
import com.example.fightingapp.toHourMinute
import com.example.fightingapp.toMonthDay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForecastScreen(latitudeLongitude: LatitudeLongitude?,viewModel: ForecastViewModel = hiltViewModel()){
    val state by viewModel.forecastConditions.collectAsState(null )
        if(latitudeLongitude != null){
             LaunchedEffect(Unit){
            viewModel.fetchCurrentLocationData(latitudeLongitude) }
        }else{
            LaunchedEffect(Unit){
            viewModel.fetchData()}
        }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Forecast") }
        )
    }
    ){
        LazyColumn{
            state?.let { it1 ->
                items(items = it1.forecastData){ item: ForecastData ->
                    ForecastRow(item = item)
                }
            }
        }
    }
}

@Composable
private fun ForecastRow(item: ForecastData){
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






