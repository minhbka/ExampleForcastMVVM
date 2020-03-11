package com.minhbka.exampleforecastmvvm.data.db

import androidx.room.TypeConverter

class Converters {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromListString(value:List<String>) = value[0]

        @TypeConverter
        @JvmStatic
        fun toListString(value: String) =  listOf(value)

    }
}