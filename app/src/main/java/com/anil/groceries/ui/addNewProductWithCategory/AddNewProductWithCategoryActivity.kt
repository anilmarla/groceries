package com.anil.groceries.ui.addNewProductWithCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anil.groceries.R
import com.anil.groceries.ui.addNewProductWithCategory.ui.main.AddNewProductWithCategoryFragment

class AddNewProductWithCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_product_with_category)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddNewProductWithCategoryFragment.newInstance())
                .commitNow()
        }
    }
}