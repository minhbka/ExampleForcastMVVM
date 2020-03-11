package com.minhbka.exampleforecastmvvm.data

import android.content.Context
import androidx.room.*
import com.minhbka.exampleforecastmvvm.data.db.Converters
import com.minhbka.exampleforecastmvvm.data.db.CurrentWeatherDao
import com.minhbka.exampleforecastmvvm.data.db.entity.CurrentWeatherEntry

@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)
@TypeConverters(Converters::class)
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