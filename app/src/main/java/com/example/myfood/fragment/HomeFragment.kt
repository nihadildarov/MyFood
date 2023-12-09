package com.example.myfood.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myfood.R
import com.example.myfood.data.Meal
import com.example.myfood.data.MealList
import com.example.myfood.databinding.FragmentHomeBinding
import com.example.myfood.retrofit.MealService
import com.example.myfood.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRandomMeal()
    }


    private fun getRandomMeal() {
        val service = RetrofitInstance.getInstance().create(MealService::class.java)
        lifecycleScope.launch(Dispatchers.IO){
            val response = service.getRandomMeal()
            if (response.isSuccessful){
                val meal:Meal? = response.body()?.let {it.meals[0]}
                withContext(Dispatchers.Main){
                    Glide.with(this@HomeFragment).load(meal?.strMealThumb).into(binding.imgRandomMeal)
                }
                Log.d("TEST","Meal ID: ${meal?.idMeal} Name: ${meal?.strMeal}")
            }else{
                val meal:Meal? = response.body()?.let {it.meals[0]}
                Log.d("TEST","Meal ID: ${meal?.idMeal} Name: ${meal?.strMeal}")
            }
        }
    }
}