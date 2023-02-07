package com.anil.groceries.ui.account

import androidx.recyclerview.widget.DiffUtil
import com.anil.groceries.model.User

class AccountFragmentDiffCallBack:DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}
