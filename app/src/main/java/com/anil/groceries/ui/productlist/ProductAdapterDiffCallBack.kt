package com.anil.groceries.ui.productlist

import androidx.recyclerview.widget.DiffUtil
import com.anil.groceries.model.Product

class ProductAdapterDiffCallBack: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}
