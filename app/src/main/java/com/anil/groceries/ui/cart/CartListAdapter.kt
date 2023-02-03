package com.anil.groceries.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.groceries.databinding.ListItemCartBinding
import com.anil.groceries.model.Product
import com.bumptech.glide.Glide

class CartListAdapter(private val listener: CartListAdapterListener) :
    ListAdapter<Product, CartListAdapter.CartListItemViewHolder>(CartAdapterDiffCallBack()) {
    class CartListItemViewHolder(val binding: ListItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, listener: CartListAdapterListener) {
            binding.itemName.text = product.name
            binding.itemPrice.text = product.price.toString()
            binding.itemQuantity.text = product.cartQuantity.toString()
            binding.itemPrice.text = (product.cartQuantity * product.price).toString()

            Glide.with(binding.root.context).load(product.image).fitCenter().into(binding.image)

            binding.btnItemAdd.setOnClickListener {
                listener.onPlusClicked(product)
            }
            binding.btnItemRemove.setOnClickListener {
                listener.onRemoveClicked(product)
            }
            binding.btnItemMinus.setOnClickListener {
                listener.onMinusClicked(product)
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListItemViewHolder {
        val binding =
            ListItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartListItemViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


}
