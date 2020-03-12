package com.minhbka.exampleforecastmvvm.data.db.network.response

import com.google.gson.annotations.SerializedName
import com.minhbka.exampleforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.minhbka.exampleforecastmvvm.data.db.entity.Location
import com.minhbka.exampleforecastmvvm.data.db.entity.Request


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)