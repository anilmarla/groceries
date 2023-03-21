package com.anil.groceries.ui.productDetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.Product
import com.anil.groceries.repository.ProductsRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailsViewModel(application: Application) : BaseViewModel(application) {
    private var productsRepository: ProductsRepository
    var product: LiveData<Product>
    private var _productId: MutableLiveData<String> = MutableLiveData()

    init {
        productsRepository = ProductsRepository(application)

        product = _productId.switchMap { input ->
            productsRepository.getProductById(input)
        }
    }

    fun setProductId(productId: String) {
        _productId.postValue(productId)
    }

    /*fun getFavouriteProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.getFavouriteProducts()
        }
    }*/

    fun update(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.update(product)
        }
    }
}