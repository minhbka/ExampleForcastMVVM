package com.minhbka.exampleforecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.minhbka.exampleforecastmvvm.data.repository.ForecastRepository
import com.minhbka.exampleforecastmvvm.internal.UnitSystem
import com.minhbka.exampleforecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC//get form setting
    val isMetric:Boolean
        get() = unitSystem == UnitSystem.METRIC


    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
