package com.anil.groceries.ui.productlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anil.groceries.R

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductListFragment.newInstance(intent.extras))
                .commitNow()
        }
    }
}