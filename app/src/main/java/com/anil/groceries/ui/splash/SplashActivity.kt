package com.anil.groceries.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anil.groceries.R
import com.anil.groceries.ui.register.RegisterFragment

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance())
                .commitNow()
        }
    }
}