package com.minhbka.exampleforecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minhbka.exampleforecastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.minhbka.exampleforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.minhbka.exampleforecastmvvm.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("Select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric():LiveData<MetricCurrentWeatherEntry>

    @Query("Select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial():LiveData<MetricCurrentWeatherEntry>
}