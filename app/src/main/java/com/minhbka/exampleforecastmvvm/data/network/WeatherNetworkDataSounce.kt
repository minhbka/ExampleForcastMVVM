package com.minhbka.exampleforecastmvvm.data.network

import androidx.lifecycle.LiveData
import com.minhbka.exampleforecastmvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSounce {
    val downloadCurrentWeather:LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location:String
    )
}