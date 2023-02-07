package com.anil.groceries.ui.account

import androidx.recyclerview.widget.DiffUtil
import com.anil.groceries.model.Account

class AccountDiffCallBack:DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }

}
