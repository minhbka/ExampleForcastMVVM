package com.minhbka.exampleforecastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.minhbka.exampleforecastmvvm.data.WeatherInterfaceApiService
import com.minhbka.exampleforecastmvvm.data.network.response.CurrentWeatherResponse
import com.minhbka.exampleforecastmvvm.internal.NoConnectivityException

class WeatherNetworkDataSounceImpl (
    private val weatherApiService:WeatherInterfaceApiService
): WeatherNetworkDataSounce {
    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchCurrentWeather = weatherApiService.getCurrentWeather(location).await()
            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity","No Internet Connection")
        }
    }
}