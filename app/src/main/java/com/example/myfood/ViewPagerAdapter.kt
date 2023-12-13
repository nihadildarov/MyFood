package com.example.myfood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfood.databinding.ItemViewPagerBinding

class ViewPagerAdapter (
    private val images:List<Int>
): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>()
{
    inner class ViewPagerViewHolder(private val binding:ItemViewPagerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(images: Int){
            binding.imgFirst.setImageResource(images)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewPagerBinding.inflate(inflater,parent,false)

        return ViewPagerViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage = images[position]
        holder.bind(curImage)
    }
}