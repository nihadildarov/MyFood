package com.example.myfood.fragment.home.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfood.data.model.MealList
import com.example.myfood.retrofit.MealService
import com.example.myfood.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeViewModel(private val mealService: MealService) : ViewModel() {
    private var _meal = MutableLiveData<Resource<MealList>>()
    val meal:LiveData<Resource<MealList>> get() = _meal



    fun getRandomMeal(){
        viewModelScope.launch(Dispatchers.IO) {
            try {

                _meal.postValue(Resource.Loading)
                val response = mealService.getRandomMeal()
                withContext(Dispatchers.Main){
                    if (response.isSuccessful){
                        response.body()?.let {
                            _meal.postValue(Resource.Success(it))
                        }
                    }
                }
            }catch (ex:Exception){
                _meal.postValue(Resource.Error(ex))
            }
        }
    }





}
