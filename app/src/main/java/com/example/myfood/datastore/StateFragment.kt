package com.example.myfood.datastore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myfood.R
import com.example.myfood.databinding.ActivityViewPagerBinding
import com.example.myfood.databinding.FragmentStateBinding

class StateFragment : Fragment() {

    private lateinit var binding: FragmentStateBinding
    private lateinit var viewModel: DataStoreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStateBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[DataStoreViewModel::class.java]
        checkThemeMode()
        switchListener()
        return binding.root
    }

    
    private fun switchListener(){
        binding.modeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            when (isChecked){
                true -> {viewModel.setTheme(true)}
                false -> {viewModel.setTheme(false)}
            }
        }
    }

    private fun checkThemeMode(){
        binding.apply {
            viewModel.getTheme.observe(viewLifecycleOwner){isDarkMode->
                when(isDarkMode){
                    true -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        modeSwitch.isChecked = true
                        modeSwitch.text = "Dark mode"
                        state.setAnimation(R.raw.night)
                    }
                    false -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        modeSwitch.isChecked = false
                        modeSwitch.text = "Light mode"
                        state.setAnimation(R.raw.day)
                    }
                }
            }
        }
    }
}