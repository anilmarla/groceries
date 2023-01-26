package com.anil.groceries.ui.explore

import androidx.recyclerview.widget.DiffUtil
import com.anil.groceries.model.Category

class CategoryAdapterDiffCallBack: DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}
