package com.anil.groceries.ui.productlist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.Product
import com.anil.groceries.model.ProductResponse
import com.anil.groceries.network.Result
import com.anil.groceries.repository.ProductsRepository
import com.anil.groceries.ui.base.BaseViewModel
import com.anil.groceries.ui.base.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListViewModel(application: Application) : BaseViewModel(application) {
    private var productsRepository: ProductsRepository
    private var _categoryId: MutableLiveData<String> = MutableLiveData()
    private var _productRemote = SingleLiveEvent<Result<Product>>()
    var products: LiveData<List<Product>>

    val productRemote: LiveData<Result<Product>>
    get() = _productRemote

    private val _productsRemote = SingleLiveEvent<Result<ProductResponse>>()
    val productsRemote: LiveData<Result<ProductResponse>>
        get() = _productsRemote

    init {
            productsRepository = ProductsRepository(application)

        products = productsRepository.getSortedProductsByName()

       /* products = _categoryId.switchMap { input ->
            productsRepository.getCategoryProductsById(input)
        }*/

        /*products = _products.switchMap {
            productsRepository.getSortedProductsByName()
        }*/
    }

    fun sortByName(){
        //_isAscending.postValue()
    }


    fun setCategoryId(categoryId: String) {
        _categoryId.postValue(categoryId)
    }

    fun update(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productsRepository.update(product)
        }
    }

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _productsRemote.postValue(Result.Loading)
            _productsRemote.postValue(productsRepository.getProducts())
        }
    }

    fun getProduct(productId: String){
        viewModelScope.launch(Dispatchers.IO) {
            _productRemote.postValue(Result.Loading)
            _productRemote.postValue(productsRepository.getProduct(productId))

        }

    }


}
