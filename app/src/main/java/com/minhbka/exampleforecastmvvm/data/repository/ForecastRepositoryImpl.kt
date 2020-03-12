package com.minhbka.exampleforecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.minhbka.exampleforecastmvvm.data.db.CurrentWeatherDao
import com.minhbka.exampleforecastmvvm.data.db.network.WeatherNetworkDataSource
import com.minhbka.exampleforecastmvvm.data.db.network.response.CurrentWeatherResponse
import com.minhbka.exampleforecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class ForecastRepositoryImpl (
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
): ForecastRepository {
    init {

        weatherNetworkDataSource.downloadCurrentWeather.observeForever {newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }
    override suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>{
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if(metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather : CurrentWeatherResponse){
        GlobalScope.launch (Dispatchers.IO){
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData(){

        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather("Seoul")
    }

    private fun isFetchCurrentNeeded(lastFetchTime:ZonedDateTime):Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}