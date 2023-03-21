package com.anil.groceries.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.groceries.databinding.ListItemCartBinding
import com.anil.groceries.databinding.ListItemFavoriteBinding
import com.anil.groceries.model.Product
import com.bumptech.glide.Glide

class FavouritesAdapter(val listener: FavouritesAdapterListener):
ListAdapter<Product, FavouritesAdapter.FavouriteListItemViewHolder>(FavouritesAdapterDiffCallBack()){
    class FavouriteListItemViewHolder(val binding: ListItemFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product, listener: FavouritesAdapterListener) {
            binding.itemName.text = product.title
            binding.itemPrice.text = product.price.toString()
            Glide.with(binding.root.context)
                .load(product.thumbnail)
                .fitCenter()
                .into(binding.image)

            binding.root.setOnClickListener {
                listener.onIconClicked(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteListItemViewHolder {
        val binding = ListItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteListItemViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}