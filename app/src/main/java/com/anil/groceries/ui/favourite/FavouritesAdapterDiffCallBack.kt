package com.anil.groceries.ui.favourite

import androidx.recyclerview.widget.DiffUtil
import com.anil.groceries.model.Product

class FavouritesAdapterDiffCallBack: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}
