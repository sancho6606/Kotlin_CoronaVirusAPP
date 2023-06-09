package com.sancho.kotlin_coronavirusapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    val retrofit=Retrofit.Builder()
        .baseUrl("https://disease.sh/v3/covid-19/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api= retrofit.create(Api::class.java)
}