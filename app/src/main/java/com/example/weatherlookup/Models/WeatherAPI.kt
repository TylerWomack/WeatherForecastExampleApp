package com.example.weatherlookup.Models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("forecast")
    fun getForecast(@Query("q") cityName : String, @Query("appid") id : String, @Query("units") type : String ): Call<WeatherContainer>
}