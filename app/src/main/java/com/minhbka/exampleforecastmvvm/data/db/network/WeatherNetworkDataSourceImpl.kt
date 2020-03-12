package com.minhbka.exampleforecastmvvm.data.db.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.minhbka.exampleforecastmvvm.data.db.network.response.CurrentWeatherResponse
import com.minhbka.exampleforecastmvvm.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl (
    private val weatherApiService: WeatherInterfaceApiService
): WeatherNetworkDataSource {
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