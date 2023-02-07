package com.anil.groceries.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.groceries.databinding.ListItemCategoryBinding
import com.anil.groceries.model.Category
import com.bumptech.glide.Glide

class CategoriesAdapter(var listener: CategoryListAdapterListener) :
    ListAdapter<Category, CategoriesAdapter.CategoryItemViewHolder>(CategoryAdapterDiffCallBack()) {
    class CategoryItemViewHolder(private val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, listener: CategoryListAdapterListener) {

            binding.categoryName.text = category.name

            Glide.with(binding.root.context).load(category.image)
                .centerCrop()
                .into(binding.image)

            binding.root.setOnClickListener {
                listener.onCardClicked(category.id, category.name)
            }

            binding.card.setCardBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    category.backgroundColor
                )
            )

            binding.card.strokeColor = ContextCompat.getColor(
                binding.root.context,
                category.borderColor
            )


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding =
            ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}






