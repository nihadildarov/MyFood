package com.example.myfood.retrofit

import pl.droidsonroids.gif.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance{
    companion object{
        private lateinit var instance:Retrofit
        fun getInstance():Retrofit{
            if(!this::instance.isInitialized){
                instance = Retrofit.Builder()
                    .baseUrl(com.example.myfood.BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }
    }
}