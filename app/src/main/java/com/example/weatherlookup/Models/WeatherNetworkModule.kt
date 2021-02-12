package com.example.weatherlookup.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherlookup.Utility.Event
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherNetworkModule {

    companion object {
        const val API_KEY = "65d00499677e59496ca2f318eb68c049"
        const val UNIT_TYPE = "imperial"
    }

    private val _weatherForecastData = MutableLiveData<List<WeatherData>>()
    val weatherForecastData : LiveData<List<WeatherData>> = _weatherForecastData

    private val _networkError = MutableLiveData<Event<Boolean>>()
    val networkError : LiveData<Event<Boolean>> = _networkError

    private fun getRetrofit() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val retrofitService: WeatherAPI by lazy {
        getRetrofit().create(WeatherAPI::class.java)
    }

    fun getWeather(city: String){
        retrofitService.getForecast(city, API_KEY, UNIT_TYPE).enqueue(object :
            Callback<WeatherContainer> {
            override fun onResponse(
                call: Call<WeatherContainer>,
                response: Response<WeatherContainer>
            ) {
                response.body()?.forecastList ?: _networkError.postValue(Event(true))
                response.body().let { container ->
                    if (null != container?.forecastList){
                        _weatherForecastData.postValue(container.forecastList)
                    }
                }
            }

            override fun onFailure(call: Call<WeatherContainer>, t: Throwable) {
                _networkError.value = Event(true)
            }
        })
    }
}