package com.minhbka.exampleforecastmvvm.data.db.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.minhbka.exampleforecastmvvm.data.db.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "1dd1c0b99adbd9f7a63397bf9f22df93"
const val BASE_URL = "http://api.weatherstack.com/"
//http://api.weatherstack.com/current?access_key=1dd1c0b99adbd9f7a63397bf9f22df93& query=London
interface WeatherInterfaceApiService {

    @GET("current")
    fun getCurrentWeather(
        @Query("query") location:String
    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): WeatherInterfaceApiService {

            val requestInterceptor = Interceptor{chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherInterfaceApiService::class.java)

        }
    }
}