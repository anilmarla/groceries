package com.anil.groceries.ui.add_category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anil.groceries.R

class AddCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddCategoryFragment.newInstance())
                .commitNow()
        }
    }
}