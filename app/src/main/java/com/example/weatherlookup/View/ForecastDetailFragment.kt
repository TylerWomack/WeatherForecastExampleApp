package com.example.weatherlookup.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherlookup.Models.WeatherData
import com.example.weatherlookup.R
import com.example.weatherlookup.ViewModels.MainViewModel
import com.example.weatherlookup.ViewModels.MainViewModel.Companion.capitalizeWords
import kotlinx.android.synthetic.main.layout_weather_detail.*

private const val ARG_POSITION = "position"

class ForecastDetailFragment : Fragment() {

    lateinit var data : WeatherData
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val position = it.getInt(ARG_POSITION)
            val list = viewModel.forecastData.value
            if (list != null && list.size > position){
                data = list[position]
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_weather_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI(){
        feelsLikeData.text = cleanUpText(data.basicInfo.feelsLike)
        temperature.text = cleanUpText(data.basicInfo.temp)
        conditions.text = data.weather.get(0).description.capitalizeWords()
        minTempData.text = cleanUpText(data.basicInfo.tempMin)
        maxTempData.text = cleanUpText(data.basicInfo.tempMax)
        setBackground()
    }

    private fun setBackground(){
        val weather = data.weather.get(0).main
        when(weather){
            SNOW -> backgroundImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.snow, null))
            RAIN -> backgroundImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.rain, null))
            CLEAR -> backgroundImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.clear, null))
            CLOUDS -> backgroundImage.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.clouds, null))
        }
        backgroundImage.alpha = 0.5f
    }

    private fun cleanUpText(unformattedTemperature : String) : String{
        return unformattedTemperature.substringBefore(".") + getString(R.string.units)
    }

    companion object {
        const val SNOW = "Snow"
        const val RAIN = "Rain"
        const val CLEAR = "Clear"
        const val CLOUDS = "Clouds"
    }
}