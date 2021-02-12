package com.example.weatherlookup.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherlookup.R
import com.example.weatherlookup.ViewModels.MainViewModel
import kotlinx.android.synthetic.main.layout_weather_list.*

class WeatherListFragment: Fragment(), WeatherViewAdapter.ForecastClickListener{
    lateinit var adapter : WeatherViewAdapter

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_weather_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.contentFrame, CityLookupFragment(), "CityLookUpFragment")
                transaction?.commit()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val forecasts = viewModel.forecastData.value
        if (forecasts != null){
            adapter = WeatherViewAdapter(forecasts, this)
            setUpRecyclerView(adapter)
        }
    }

    private fun setUpRecyclerView(weatherAdapter: WeatherViewAdapter){
        WeatherRecyclerView.apply {
            setHasFixedSize(true)
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    override fun onForecastSelected(position: Int) {
        val fragment = ForecastDetailFragment()
        val args = Bundle()
        args.putInt("position", position)
        fragment.arguments = args
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.contentFrame, fragment)
            .commit()
    }
}
