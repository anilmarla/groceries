package com.anil.groceries.ui.additem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anil.groceries.R

class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddItemFragment.newInstance())
                .commitNow()
        }
    }
}