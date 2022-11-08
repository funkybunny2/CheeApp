package com.example.fightingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fightingapp.ui.CurrentConditionsScreen
import com.example.fightingapp.ui.ForecastScreen

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "CurrentConditions" ){
                composable("CurrentConditions") {
                    CurrentConditionsScreen(
                        stringResource(id = R.string.city_name),
                        stringResource(id = R.string.current_temp, 56)
                    ){
                        navController.navigate("Forecast")
                    }
                }
                composable("Forecast"){
                    ForecastScreen()
                }
            }
        }
    }
}