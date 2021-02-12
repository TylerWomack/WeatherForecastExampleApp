package com.example.weatherlookup.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherlookup.R
import com.example.weatherlookup.ViewModels.MainViewModel
import kotlinx.android.synthetic.main.search_screen.*

class CityLookupFragment : Fragment() {

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButton.setOnClickListener{
            viewModel.getWeatherForCity(textInput.text.toString())
        }
    }

    private fun setUpObservers(){
        viewModel.forecastData.observe(viewLifecycleOwner, Observer { forecastList ->
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.contentFrame, WeatherListFragment(), "WeatherListFragment")
            transaction?.commit()
        })
    }
}