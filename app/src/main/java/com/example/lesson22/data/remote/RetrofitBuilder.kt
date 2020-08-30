package com.example.lesson22.data.remote

import com.example.lesson22.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private var service: CurrencyService? = null

    fun getService():CurrencyService?{
        if (service == null)
            service = buildRetrofit()

        return service
    }

    private fun buildRetrofit():CurrencyService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyService::class.java)
    }
}