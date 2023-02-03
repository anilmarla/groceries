package com.anil.groceries.ui.product_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anil.groceries.R

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    ProductDetailsFragment.newInstance(intent.extras)
                ).commitNow()

        }
    }
}