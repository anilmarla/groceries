package com.anil.groceries.ui.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.groceries.databinding.ListItemProductBinding
import com.anil.groceries.model.Product
import com.bumptech.glide.Glide

class ProductListAdapter(private val listener: ProductListAdapterListener) :
    ListAdapter<Product, ProductListAdapter.ProductItemVIewHolder>(ProductAdapterDiffCallBack()) {
    class ProductItemVIewHolder(val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, listener: ProductListAdapterListener) {
            binding.itemName.text = product.name
            binding.itemPrice.text = product.price.toString()

            Glide.with(binding.root.context).load(product.image)
                .fitCenter()
                .into(binding.image)

            binding.root.setOnClickListener {
                listener.onIconClicked(product)
            }
            binding.btnAdd.setOnClickListener {
                listener.onAddBtnClicked(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemVIewHolder {
        val binding =
            ListItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemVIewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemVIewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

}