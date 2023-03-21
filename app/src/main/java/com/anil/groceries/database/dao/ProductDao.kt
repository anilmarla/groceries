package com.anil.groceries.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anil.groceries.model.Product

@Dao
interface ProductDao {

    @Query("select *from products where categoryId=:categoryId")
    fun getCategoryProducts(categoryId: String): LiveData<List<Product>>

    @Query("Select * from products where isAddedCart=1")
    fun getCartProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(product: Product)

    @Query("SELECT * from products ORDER BY title ASC")
    fun getAll(): LiveData<List<Product>>

    @Delete
    fun remove(product: Product)

    @Query("select * from products where isAddedFavourite=1")
    fun getFavouriteProducts(): LiveData<List<Product>>

    @Query("select * from products where id=:id limit 1")
    fun getProduct(id: String): LiveData<Product>

    @Query("SELECT * FROM products ORDER BY title ASC")
    fun sortedByName(): LiveData<List<Product>>


}