package com.example.fightingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ForecastAdapter(private val data: List<DayForecast>) : RecyclerView.Adapter<ForecastViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {

       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.view_forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {

        holder.bind(data[position])

    }

    override fun getItemCount(): Int = data.size

}

class ForecastViewHolder(view : View) : RecyclerView.ViewHolder(view){
    private val forecaster_temp: TextView
    private val date: TextView
    private val forcaster_high: TextView
    private val forcaster_low: TextView
    private val sunrise: TextView
    private val sunset: TextView

    init{


        forecaster_temp = view.findViewById(R.id.forecast_temp)

        date = view.findViewById(R.id.forecast_date)

        forcaster_high = view.findViewById(R.id.forecast_high)

        forcaster_low = view.findViewById(R.id.forecast_low)


        sunrise = view.findViewById(R.id.forecast_sunrise)
        sunset = view.findViewById(R.id.forecast_sunset)
    }

    fun bind(data: DayForecast){
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val dateTime = LocalDateTime.ofEpochSecond(data.Date,0, ZoneOffset.of("-5"))
        val formattedDate = formatter.format(dateTime)

        val sunriseTime = LocalDateTime.ofEpochSecond(data.sunrise,0, ZoneOffset.of("-5"))
        val sunsetTime = LocalDateTime.ofEpochSecond(data.sunset,0, ZoneOffset.of("-5"))
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
        val formattedSunRiseTime = timeFormatter.format(sunriseTime);
        val formattedSunSetTime = timeFormatter.format(sunsetTime);


        forecaster_temp.text = "Temp: "+ data.temp.day.toString()+"°"
        date.text = formattedDate
        forcaster_high.text = "High: "+data.temp.max.toString()+"°"
        forcaster_low.text = "Low: "+data.temp.min.toString()+"°"
        sunrise.text ="sunrise: "+ formattedSunRiseTime
        sunset.text = "sunset: "+formattedSunSetTime
    }
}