package com.anil.groceries.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.anil.groceries.database.AppDatabase
import com.anil.groceries.database.dao.ProductDao
import com.anil.groceries.model.Product
import com.anil.groceries.model.ProductResponse
import com.anil.groceries.network.*
import com.anil.groceries.ui.base.BaseRepository

class ProductsRepository(application: Application) : BaseRepository() {
    private val productDao: ProductDao
    private var apiService: ApiService

    init {
        productDao = AppDatabase.getDatabase(application).productDao()
        apiService = Api.getInstance(application.applicationContext).apiService
    }

    fun getAll(): LiveData<List<Product>> {
        return productDao.getAll()
    }

    fun insert(product: Product) {
        productDao.insert(product)
    }

    fun update(product: Product) {
        productDao.update(product)
    }

    fun getCategoryProductsById(categoryId: String): LiveData<List<Product>> {
        //return productDao.getCategoryProducts(categoryId)
        return productDao.getAll()
    }

    fun getCartProducts(): LiveData<List<Product>> {
        return productDao.getCartProducts()
    }

    fun getFavouriteProducts(): LiveData<List<Product>> {
        return productDao.getFavouriteProducts()
    }

    fun getProductById(productId: String): LiveData<Product> {
        return productDao.getProduct(productId)
    }

    fun getSortedProductsByName(): LiveData<List<Product>> {
        return productDao.sortedByName()
    }

    suspend fun getProducts(): Result<ProductResponse> {
        val result = getResult { apiService.getProducts() }

        if (result.succeeded) {
            result.responseData?.products?.let {
                // insert products into db
                /*it.forEach { p ->
                    productDao.insert(p)
                }*/
                productDao.insertAll(it)
            }
        }
        return result
    }

    suspend fun getProduct(productId: String): Result<Product> {
        val result = getResult { apiService.getProduct(productId) }

        if (result.succeeded) {
            result.responseData?.let {
                productDao.insert(it)
            }
        }
        return result
    }
}