package com.example.myfood.retrofit

import com.example.myfood.data.MealList
import retrofit2.Response
import retrofit2.http.GET

interface MealService {

    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealList>
}