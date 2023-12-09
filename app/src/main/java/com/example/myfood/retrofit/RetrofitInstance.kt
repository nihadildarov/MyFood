package com.example.myfood.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance{
    companion object{
        private lateinit var instance:Retrofit
        fun getInstance():Retrofit{
            if(!this::instance.isInitialized){
                instance = Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }
    }
}