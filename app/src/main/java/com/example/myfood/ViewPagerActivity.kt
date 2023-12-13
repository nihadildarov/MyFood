package com.example.myfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfood.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)

        createListImages()
        setContentView(binding.root)
    }

    private fun createListImages(){
        val images = listOf(
            R.drawable.my_ss,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_location,
            R.drawable.ic_btm_category,
            R.drawable.ic_btm_home,
            R.drawable.ic_search,
            R.drawable.ic_video,
        )

        val adapter = ViewPagerAdapter(images)
        binding.viewPager.adapter = adapter
        binding.viewPager.beginFakeDrag()
        binding.viewPager.fakeDragBy(-10f)
        binding.viewPager.endFakeDrag()
    }


}