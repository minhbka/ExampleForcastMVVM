package com.minhbka.exampleforecastmvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minhbka.exampleforecastmvvm.data.db.CurrentWeatherDao
import com.minhbka.exampleforecastmvvm.data.db.entity.CurrentWeatherEntry

@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)
abstract class ForecastDatabase : RoomDatabase(){
    abstract fun currentWeatherDao():CurrentWeatherDao
    companion object{
        @Volatile private var instance:ForecastDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: Room.databaseBuilder(context.applicationContext, ForecastDatabase::class.java, "forecast.db").build().also {
                    instance = it
                }
            
        }
    }
}