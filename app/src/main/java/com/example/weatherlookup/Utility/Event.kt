package com.example.weatherlookup.Utility

open class Event<out T>(private val content: T){
    private var hasBeenHandled = false

    @Synchronized
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled){
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}