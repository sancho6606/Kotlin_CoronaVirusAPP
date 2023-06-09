package com.sancho.kotlin_coronavirusapp.network

import com.sancho.kotlin_coronavirusapp.model.Corona
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    //https://disease.sh/v3/covid-19/countries/

    @GET("countries")
    fun getAllCountries():Call<ArrayList<Corona>>

    @GET("countries/{name}")
    fun getAllCountryData(@Path("name") name:String):Call<Corona>
}