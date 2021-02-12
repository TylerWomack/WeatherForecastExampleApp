package com.example.weatherlookup.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherlookup.Models.WeatherData
import com.example.weatherlookup.R
import com.example.weatherlookup.ViewModels.MainViewModel.Companion.capitalizeWords


class WeatherViewAdapter(var forecasts : List<WeatherData>, private val clickListener : ForecastClickListener) : RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val v : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_weather_summary, parent, false)
        return WeatherViewHolder(v)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.layout.setOnClickListener {
            clickListener.onForecastSelected(holder.adapterPosition)
        }
        setHolderText(holder, position)
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }


    private fun setHolderText(holder: WeatherViewHolder, position: Int){
        holder.description.text = forecasts[position].weather[0].description.capitalizeWords()
        val temperature = forecasts[position].basicInfo.temp.substringBefore(".") + "F"
        holder.temperature.text = temperature
    }

        interface ForecastClickListener {
        fun onForecastSelected(position: Int)
    }
}