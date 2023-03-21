package com.anil.groceries.ui.productlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.groceries.databinding.ListItemProductBinding
import com.anil.groceries.model.Product
import com.anil.groceries.utils.ProductUtils.Companion.formatPrice
import com.bumptech.glide.Glide


class ProductListAdapter(
    private val listener: ProductListAdapterListener,
    private val context: Context?
) :
    ListAdapter<Product, ProductListAdapter.ProductItemVIewHolder>(
        ProductAdapterDiffCallBack()
    ) {
    class ProductItemVIewHolder(val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            product: Product,
            listener: ProductListAdapterListener,
            context: Context?
        ) {
            binding.itemName.text = product.title
            binding.itemPrice.text = formatPrice(context = context, product.price)

            Glide.with(binding.root.context).load(product.thumbnail)
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
        holder.bind(getItem(position), listener, context)
    }

}
