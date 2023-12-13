package com.example.myfood.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myfood.R
import com.example.myfood.ViewPagerActivity
import com.example.myfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btmNavController()
    }


    private fun btmNavController() {
        val navHost = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        val navController = navHost.navController
        binding.btmNav.setupWithNavController(navController)
        btmNavVisibilityControl(navController)
    }


    private fun btmNavVisibilityControl(navController: NavController) {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.randomMealDetailFragment -> binding.btmNav.visibility = View.GONE
                R.id.blurFragment -> binding.btmNav.visibility = View.GONE
                else -> binding.btmNav.visibility = View.VISIBLE
            }
        }
    }



}