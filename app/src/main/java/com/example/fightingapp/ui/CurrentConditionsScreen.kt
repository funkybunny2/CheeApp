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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fightingapp.R
import java.time.format.TextStyle


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentConditionsScreen(cityName: String, temperature: String,onForecastButtonClick: () -> Unit){
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }
        )
    }
    ){
        CurrentConditionsContent(cityName, temperature,onForecastButtonClick)
    }
}

@Composable
private fun CurrentConditionsContent(cityName: String, temperature: String, onForecastButtonClick: () -> Unit,){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = cityName,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(400)
            )
        )
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column() {
                Text(text=temperature,
                    fontSize = 72.sp, fontWeight = FontWeight(600),
                    textAlign = TextAlign.Start
                )
                Text(text = stringResource(id = R.string.feels_like, 82),fontSize = 18.sp)
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
                text = stringResource(id = R.string.low, 65),fontSize = 18.sp
            )
            Text(
                text = stringResource(id = R.string.high, 80),fontSize = 18.sp
            )
            Text(
                text = stringResource(id = R.string.humidity, 100),fontSize = 18.sp
            )
            Text(text = stringResource(id = R.string.pressure, 1013),fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(72.dp))
        Button(onClick = onForecastButtonClick) {
            Text(text = stringResource(id = R.string.forecast))
        }
    }

}

@Preview(
    "CurrentConditions",device = Devices.PIXEL_4,showBackground = true,showSystemUi = true,
)
@Composable
fun CurrentConditionsScreenPreview(){
        CurrentConditionsScreen("St, Paul, MN", "52"){}
}