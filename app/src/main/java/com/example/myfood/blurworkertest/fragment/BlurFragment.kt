package com.example.myfood.blurworkertest.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.myfood.R
import com.example.myfood.blurworkertest.viewmodel.BlurViewModel
import com.example.myfood.blurworkertest.viewmodel.BlurViewModelFactory
import com.example.myfood.databinding.FragmentBlurBinding
class BlurFragment : Fragment() {

    private lateinit var binding: FragmentBlurBinding
    private val viewModel : BlurViewModel by viewModels {
        BlurViewModelFactory(
            requireActivity().application
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlurBinding.inflate(inflater,container,false)
        gotBtnClick()
        return binding.root
    }

    private fun gotBtnClick(){
        binding.goButton.setOnClickListener { viewModel.applyBlur(blurLevel)
        }

    }

    private val blurLevel: Int
        get() =
            when (binding.radioBlurGroup.checkedRadioButtonId) {
                R.id.radio_blur_lv_1 -> 1
                R.id.radio_blur_lv_2 -> 3
                R.id.radio_blur_lv_3 -> 5
                else -> 1
            }

}