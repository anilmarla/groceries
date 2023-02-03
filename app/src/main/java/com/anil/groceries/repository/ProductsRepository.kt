package com.anil.groceries.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.anil.groceries.database.AppDatabase
import com.anil.groceries.database.dao.ProductDao
import com.anil.groceries.model.Product

class ProductsRepository(application: Application) {
    private val productDao: ProductDao

    init {
        productDao = AppDatabase.getDatabase(application).productDao()
    }

    fun insert(product: Product) {
        productDao.insert(product)
    }

    fun update(product: Product) {
        productDao.update(product)
    }

    fun getCategoryProductsById(categoryId: String): LiveData<List<Product>> {
        return productDao.getCategoryProducts(categoryId)
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

  /*  fun removeProduct(product: Product){
        productDao.remove(product)
    }*/
}