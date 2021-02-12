package com.example.weatherlookup.View

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_weather_summary.view.*

class WeatherViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val description : AppCompatTextView = itemView.description
    val temperature : AppCompatTextView = itemView.temperature
    val layout : LinearLayout = itemView.summaryLayout
}