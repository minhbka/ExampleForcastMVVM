package com.minhbka.exampleforecastmvvm.data.db.network

import androidx.lifecycle.LiveData
import com.minhbka.exampleforecastmvvm.data.db.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadCurrentWeather:LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location:String
    )
}