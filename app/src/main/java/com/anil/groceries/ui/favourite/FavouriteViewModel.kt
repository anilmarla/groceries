package com.anil.groceries.ui.favourite

import android.app.Application
import androidx.lifecycle.LiveData
import com.anil.groceries.model.Product
import com.anil.groceries.repository.ProductsRepository
import com.anil.groceries.ui.base.BaseViewModel

class FavouriteViewModel(application: Application) : BaseViewModel(application) {
    private var productsRepository: ProductsRepository

    var favouriteProduct: LiveData<List<Product>>

    init {
        productsRepository = ProductsRepository(application)

        favouriteProduct = productsRepository.getFavouriteProducts()
    }


}
