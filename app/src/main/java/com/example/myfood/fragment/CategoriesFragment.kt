package com.example.myfood.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfood.R
import com.example.myfood.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {
    private lateinit var binding:FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

}