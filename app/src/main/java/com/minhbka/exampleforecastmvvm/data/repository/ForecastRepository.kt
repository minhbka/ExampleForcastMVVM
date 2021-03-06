package com.minhbka.exampleforecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.minhbka.exampleforecastmvvm.data.db.entity.WeatherLocation
import com.minhbka.exampleforecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric : Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation():LiveData<WeatherLocation>
}