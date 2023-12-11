package com.example.myfood.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myfood.R
import com.example.myfood.databinding.FragmentHomeBinding
import com.example.myfood.fragment.home.viewmodel.HomeViewModel
import com.example.myfood.fragment.home.viewmodel.HomeViewModelFactory
import com.example.myfood.retrofit.MealService
import com.example.myfood.retrofit.RetrofitInstance
import com.example.myfood.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(RetrofitInstance.getInstance().create(MealService::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blurPageOpen()
        viewModel.getRandomMeal()
        observeRandomMeal()
        goToRandomMealDetail()
    }


    private fun observeRandomMeal() {
        viewModel.meal.observe(viewLifecycleOwner) {

            when (it) {
                Resource.Loading -> {
                    binding.progressRandomMeal.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressRandomMeal.visibility = View.INVISIBLE
                    Glide.with(this@HomeFragment).load(it.data.meals[0].strMealThumb)
                        .into(binding.imgRandomMeal)

                }

                is Resource.Error -> {
                    binding.progressRandomMeal.visibility = View.INVISIBLE
                }
            }
        }
    }


    private fun goToRandomMealDetail() {
        binding.imgRandomMeal.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_randomMealDetailFragment)
        }
    }

    private fun blurPageOpen(){
        binding.txtHeadHome.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_blurFragment)
        }
    }


}