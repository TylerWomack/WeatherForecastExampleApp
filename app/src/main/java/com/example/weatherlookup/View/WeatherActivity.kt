package com.example.weatherlookup.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherlookup.R
import com.example.weatherlookup.ViewModels.MainViewModel

class WeatherActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main_activity)
        setUpObservable()
        launchSearchFragment()
    }

    private fun setUpObservable(){
        viewModel.error.observe(this, Observer { event ->
            val error = event.getContentIfNotHandled()
            if (error != null && !error){
                Toast.makeText(this, "Sorry, we weren't able to complete your request", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun launchSearchFragment(){
        val fragment =
            CityLookupFragment()
            supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentFrame, fragment)
            .commit()
    }
}