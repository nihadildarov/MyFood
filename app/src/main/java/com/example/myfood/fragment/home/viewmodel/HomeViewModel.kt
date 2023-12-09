package com.example.myfood.fragment.home.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfood.data.MealList
import com.example.myfood.retrofit.MealService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val mealService: MealService) : ViewModel() {
    private var _meal = MutableLiveData<MealList>()
    val meal:LiveData<MealList> get() = _meal



    fun getRandomMeal(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = mealService.getRandomMeal()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let {
                        _meal.postValue(it)
                    }
                }
            }
        }
    }





}
