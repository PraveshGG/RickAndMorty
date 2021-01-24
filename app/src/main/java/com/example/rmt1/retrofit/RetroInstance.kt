package com.example.rmt1.retrofit


import com.example.rmt1.helpers.MystringUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {
        fun getRetroInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(MystringUtils.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}