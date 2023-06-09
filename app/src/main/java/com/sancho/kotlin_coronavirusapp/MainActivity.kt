package com.sancho.kotlin_coronavirusapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.sancho.kotlin_coronavirusapp.databinding.ActivityMainBinding
import com.sancho.kotlin_coronavirusapp.model.Corona
import com.sancho.kotlin_coronavirusapp.network.RetrofitInstance.api
import ir.mahozad.android.PieChart
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var coronaAdapter: CoronaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttongorequest.setOnClickListener {
                getCountryData(edittextcountryname.text.toString())
            }
        }



    }

    fun getCountryData(name:String){
        val call:Call<Corona> = api.getAllCountryData(name)
        call.enqueue(object :Callback<Corona>{
            override fun onResponse(call: Call<Corona>, response: Response<Corona>) {
                if (response.isSuccessful){
                    val corona=response.body()
                    Log.d("sancho","onResponse: countryinfo ${response.code()}")
                    binding.apply {
                        textviewcountryname.text=corona?.country
                        textviewcountrystats.text="Cases :${corona?.cases} Recovered :${corona?.recovered} Deaths :${corona?.deaths}"
                        Glide.with(imageviewcountryflag).load(corona?.countryInfo?.flag).into(imageviewcountryflag)

                        //Pie Chart
                        val all=corona?.recovered?.toFloat()!!+corona?.cases?.toFloat()!!+corona?.deaths?.toFloat()!!
                        var recovered=corona?.recovered*100/all
                        var cases=corona?.cases*100/all
                        var deaths=corona?.deaths*100/all

                        

                        binding.pieChart.slices = listOf(
                            PieChart.Slice(recovered, Color.GREEN),
                            PieChart.Slice(cases, Color.BLUE),
                            PieChart.Slice(deaths, Color.RED),



//                            PieChart.Slice(0.3f, Color.GREEN),
//                            PieChart.Slice(0.5f, Color.BLUE),
//                            PieChart.Slice(0.2f, Color.RED),
                        )
                        textviewcountryname.text="${corona?.recovered*100/all} %  ${corona?.cases*100/all} %  ${corona?.deaths*100/all}"
                    }
                }
            }

            override fun onFailure(call: Call<Corona>, t: Throwable) {

            }
        })
    }

    fun getAllCountry(){
        val call:Call<ArrayList<Corona>> = api.getAllCountries()
        call.enqueue(object :Callback<ArrayList<Corona>>{
            override fun onResponse(
                call: Call<ArrayList<Corona>>,
                response: Response<ArrayList<Corona>>
            ) {
                if (response.isSuccessful){
                    Log.d("sancho","onResponse: ${response.code()}")
                    val arrayList=response.body()!!

                    binding.apply {
                        recyclerview.layoutManager=GridLayoutManager(this@MainActivity,2)
                        coronaAdapter= CoronaAdapter(this@MainActivity, arrayList)
                        recyclerview.adapter=coronaAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Corona>>, t: Throwable) {

            }
        })
    }

}