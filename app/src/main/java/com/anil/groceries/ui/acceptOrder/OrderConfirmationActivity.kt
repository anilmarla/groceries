package com.anil.groceries.ui.acceptOrder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anil.groceries.R

class OrderConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OrderConfirmationFragment.newInstance())
                .commitNow()
        }
    }
}