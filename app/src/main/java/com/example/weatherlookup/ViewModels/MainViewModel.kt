package com.example.weatherlookup.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherlookup.Models.WeatherData
import com.example.weatherlookup.Models.WeatherNetworkModule
import com.example.weatherlookup.Utility.Event

class MainViewModel : ViewModel(){
    private val networkModule: WeatherNetworkModule by lazy {
        WeatherNetworkModule()
    }

    val forecastData : LiveData<List<WeatherData>> = networkModule.weatherForecastData
    val error : LiveData<Event<Boolean>> = networkModule.networkError

    fun getWeatherForCity(city : String){
        networkModule.getWeather(city)
    }

    //https://stackoverflow.com/questions/52042903/capitalise-every-word-in-string-with-extension-function
    companion object{
        fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")
    }
}