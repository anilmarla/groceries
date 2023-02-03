package com.anil.groceries.ui.cart

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.Product
import com.anil.groceries.repository.ProductsRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : BaseViewModel(application) {
    private var productsRepository: ProductsRepository

    var cartProducts: LiveData<List<Product>>

    init {
        productsRepository = ProductsRepository(application)

        cartProducts = productsRepository.getCartProducts()
    }

   /* fun remove(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.removeProduct(product)
        }
    }*/

    fun update(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.update(product)
        }
    }
}