package com.anil.groceries.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anil.groceries.R
import com.anil.groceries.databinding.ActivityMainBinding
import com.anil.groceries.ui.account.AccountFragment
import com.anil.groceries.ui.cart.CartFragment
import com.anil.groceries.ui.explore.ExploreFragment
import com.anil.groceries.ui.favourite.FavouriteFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(getString(R.string.explore), ExploreFragment.newInstance())
        binding.fab.show()

        binding.apply {
            bottomNavigation.setOnItemSelectedListener {

                binding.fab.hide()

                when (it.itemId) {
                    R.id.explore -> {
                        loadFragment(
                            getString(R.string.explore),
                            ExploreFragment.newInstance()
                        )

                        binding.fab.show()
                    }

                    R.id.cart -> loadFragment(getString(R.string.cart), CartFragment.newInstance())

                    R.id.favourite -> loadFragment(
                        getString(R.string.favourite), FavouriteFragment.newInstance()
                    )

                    R.id.account -> loadFragment(
                        getString(R.string.account),
                        AccountFragment.newInstance()
                    )
                }

                return@setOnItemSelectedListener true
            }
        }
    }

    private fun loadFragment(title: String, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        setTitle(title)
    }
}