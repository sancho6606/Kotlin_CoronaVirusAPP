package com.sancho.kotlin_coronavirusapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sancho.kotlin_coronavirusapp.databinding.ActivityMainBinding
import com.sancho.kotlin_coronavirusapp.databinding.RecyclerviewItemBinding
import com.sancho.kotlin_coronavirusapp.model.Corona

class CoronaAdapter constructor(
    val context: Context,
    val arrayList: ArrayList<Corona>
):RecyclerView.Adapter<CoronaAdapter.CoronaViewHolder>(){


    class CoronaViewHolder(val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoronaViewHolder {
        val view=RecyclerviewItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CoronaViewHolder(view)
    }
    override fun onBindViewHolder(holder: CoronaViewHolder, position: Int) {
        holder.binding.apply {
            textviewcountryname.text=arrayList.get(position).country
            textviewcountrystats.text="Cases :${arrayList.get(position).cases} Recovered :${arrayList.get(position).recovered} Death :${arrayList.get(position).deaths}"
            Glide.with(imageviewcountryflag).load(arrayList.get(position).countryInfo.flag).into(imageviewcountryflag)
        }
    }
    override fun getItemCount(): Int =arrayList.size
    }