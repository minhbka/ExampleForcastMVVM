package com.minhbka.exampleforecastmvvm.data.db

import android.content.Context
import androidx.room.*
import com.minhbka.exampleforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.minhbka.exampleforecastmvvm.data.db.entity.WeatherLocation

@Database(
    entities = [CurrentWeatherEntry::class, WeatherLocation::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ForecastDatabase : RoomDatabase(){
    abstract fun currentWeatherDao():CurrentWeatherDao
    abstract fun weatherLocationDao():WeatherLocationDao
    companion object{
        @Volatile private var instance: ForecastDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
            instance ?: Room.databaseBuilder(context.applicationContext, ForecastDatabase::class.java, "forecast.db").build().also {
                    instance = it
                }

        }
    }
}