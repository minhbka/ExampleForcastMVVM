package com.minhbka.exampleforecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.minhbka.exampleforecastmvvm.R
import com.minhbka.exampleforecastmvvm.data.WeatherInterfaceApiService
import com.minhbka.exampleforecastmvvm.data.network.ConnectivityInterceptor
import com.minhbka.exampleforecastmvvm.data.network.ConnectivityInterceptorImpl
import com.minhbka.exampleforecastmvvm.data.network.WeatherNetworkDataSounceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {


    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)


        val apiService = WeatherInterfaceApiService(ConnectivityInterceptorImpl(this.context!!))
        val weatherNetworkDataSounce = WeatherNetworkDataSounceImpl(apiService)
        weatherNetworkDataSounce.downloadCurrentWeather.observe(this, Observer {
            textView.text = it.toString()
        })
        GlobalScope.launch(Dispatchers.Main){
            weatherNetworkDataSounce.fetchCurrentWeather("Seoul")
        }
    }

}
