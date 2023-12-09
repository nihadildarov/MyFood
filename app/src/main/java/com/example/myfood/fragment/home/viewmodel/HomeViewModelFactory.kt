package com.example.myfood.fragment.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myfood.retrofit.MealService

class HomeViewModelFactory(private val mealService: MealService):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mealService) as T
    }
}