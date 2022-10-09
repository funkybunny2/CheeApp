package com.example.fightingapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.fightingapp.databinding.FragmentForecastBinding


private val forecastData = listOf(
    DayForecast(898493015L,687096501L,32, ForecastTemp(93f,23f,66f),34f,12),
    DayForecast(929607143L,687096501L,33,ForecastTemp(71f,12f,55f),34f,12),
    DayForecast(211224254L,687096501L,34,ForecastTemp(44f,55f,70f),34f,12),
    DayForecast(721499407L,687096501L,35,ForecastTemp(31f,34f,60f),34f,12),
    DayForecast(659937178L,687096501L,36,ForecastTemp(21f,23f,40f),34f,12),
    DayForecast(506053831L,687096501L,37,ForecastTemp(100f,67f,80f),34f,12),
    DayForecast(687096501L,687096501L,38,ForecastTemp(99f,73f,90f),34f,12),
    DayForecast(461220358L,687096501L,39,ForecastTemp(44f,84f,99f),34f,12),
    DayForecast(934138683L,687096501L,40,ForecastTemp(55f,99f,100f),34f,12),
    DayForecast(139421205L,687096501L,41,ForecastTemp(56f,11f,20f),34f,12),
    DayForecast(121541913L,687096501L,42,ForecastTemp(24f,23f,40f),34f,12),
    DayForecast(197492154L ,687096501L,43,ForecastTemp(22f,66f,77f),34f,12),
    DayForecast(935241248L,687096501L,44,ForecastTemp(66f,45f,50f),34f,12),
    DayForecast(625131065L,687096501L,45,ForecastTemp(87f,34f,42f),34f,12),
    DayForecast(123539355L,687096501L,46,ForecastTemp(91f,77f,80f),34f,12),
    DayForecast(423702741L,687096501L,47,ForecastTemp(47f,22f,30f),34f,12)


)

class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    private lateinit var binding: FragmentForecastBinding
    private val args : ForecastFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)
        binding.forecastList.adapter = ForecastAdapter(forecastData)


    }
}