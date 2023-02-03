package com.anil.groceries.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anil.groceries.model.Category

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Query("Select * from categories")
    fun getAll(): LiveData<List<Category>>

}