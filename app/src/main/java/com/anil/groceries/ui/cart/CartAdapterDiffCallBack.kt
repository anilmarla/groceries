package com.anil.groceries.ui.cart

import androidx.recyclerview.widget.DiffUtil
import com.anil.groceries.model.Product

class CartAdapterDiffCallBack: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}
