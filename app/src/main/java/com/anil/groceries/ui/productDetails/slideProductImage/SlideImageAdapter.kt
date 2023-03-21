package com.anil.groceries.ui.productDetails.slideProductImage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anil.groceries.databinding.ListItemViewpagerBinding
import com.bumptech.glide.Glide

class SlideImageAdapter(private val images: List<String>) :
    RecyclerView.Adapter<SlideImageAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val binding: ListItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            Glide.with(binding.root.context)
                .load(image)
                .fitCenter()
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemViewpagerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}