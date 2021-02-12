package com.example.weatherlookup.Models

import com.google.gson.annotations.SerializedName

data class WeatherData (
    @SerializedName("main") val basicInfo : BasicWeatherInfo,
    @SerializedName("weather") val weather : List<Weather>
)

data class Weather (
    @SerializedName("description") var description : String,
    @SerializedName("main") val main : String
    )

data class BasicWeatherInfo(
    @SerializedName("feels_like") var feelsLike : String,
    @SerializedName("temp") var temp : String,
    @SerializedName("temp_min") var tempMin : String,
    @SerializedName("temp_max") var tempMax : String
)

data class WeatherContainer(
      @SerializedName("list") val forecastList : List<WeatherData>,
      @SerializedName("city") val city : CityData
)

data class CityData(
    @SerializedName("name") val name : String,
    @SerializedName("population") val population : Int
)


