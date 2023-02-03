package com.anil.groceries.ui.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anil.groceries.R

class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CartFragment.newInstance(intent.extras)).commitNow()
        }
    }
}