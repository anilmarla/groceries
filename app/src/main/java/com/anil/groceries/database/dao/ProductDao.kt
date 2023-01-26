package com.anil.groceries.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.anil.groceries.model.Product

@Dao
interface ProductDao {

    @Query("select *from products where categoryId=:categoryId")
    fun getCategoryProducts(categoryId: String): LiveData<List<Product>>

    @Query("Select * from products where isAddedCart=true")
    fun getCartProducts()
}