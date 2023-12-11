package com.example.myfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myfood.databinding.FragmentRandomMealDetailBinding

class RandomMealDetailFragment : Fragment() {
    private lateinit var binding: FragmentRandomMealDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomMealDetailBinding.inflate(inflater,container,false)
        uploadWorkerInit()

        return binding.root
    }



    private fun uploadWorkerInit(){
        binding.imgBtnVideoRandomMeal.setOnClickListener{
            val oneTimeWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
                .build()
            WorkManager.getInstance(requireContext()).enqueue(oneTimeWorkRequest)
        }
    }

}