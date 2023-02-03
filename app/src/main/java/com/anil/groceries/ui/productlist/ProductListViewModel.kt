package com.anil.groceries.ui.productlist

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

class ProductListViewModel(application: Application) : BaseViewModel(application) {
    private var productsRepository: ProductsRepository
    private var _categoryId: MutableLiveData<String> = MutableLiveData()

    var products: LiveData<List<Product>>

    init {
        productsRepository = ProductsRepository(application)

        products = _categoryId.switchMap { input ->
            productsRepository.getCategoryProductsById(input)
        }
    }

    fun setCategoryId(categoryId: String) {
        _categoryId.postValue(categoryId)
    }

    fun update(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.update(product)
        }
    }
}
