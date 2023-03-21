package com.anil.groceries.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.groceries.databinding.ListItemAccountBinding
import com.anil.groceries.model.Account
import com.bumptech.glide.Glide

class AccountFragmentAdapter: ListAdapter<Account, AccountFragmentAdapter.AccountViewHolder>(AccountDiffCallBack()) {
    class AccountViewHolder(val binding: ListItemAccountBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind(account: Account, position: Int){
            binding.name.text = account.name

            Glide.with(binding.root.context).load(account.icon).centerCrop().into(binding.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding = ListItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = getItem(position)
        holder.bind(account, position)
    }
}