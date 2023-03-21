package com.anil.groceries.ui.additem

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.Product
import com.anil.groceries.model.User
import com.anil.groceries.repository.ProductsRepository
import com.anil.groceries.repository.UsersRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddItemViewModel(application: Application) : BaseViewModel(application) {
    private var productsRepository: ProductsRepository

    init {
        productsRepository = ProductsRepository(application)
    }

   /* fun addProduct(name: String, price: Int, image: String, categoryId: String) {
        val product = Product(
            id = UUID.randomUUID().toString(),
            name = name,
            price = price,
            image = image,
            categoryId = categoryId

        )
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.insert(product)
        }
    }*/

    fun getCategoryProductsById(categoryId: String){
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.getCategoryProductsById(categoryId)
        }
    }
}