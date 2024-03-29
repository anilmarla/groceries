package com.anil.groceries.network

import android.content.Context
import com.anil.groceries.R
import java.io.IOException


class NoConnectivityException(private val context: Context) : IOException() {
    override val message: String
        get() = context.getString(R.string.no_internet_message)
}