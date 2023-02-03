package com.anil.groceries.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anil.groceries.database.AppDatabase
import com.anil.groceries.database.dao.CategoryDao
import com.anil.groceries.model.Category
import com.anil.groceries.model.Product

class CatergoriesRepository(application: Application) {

    private val categoryDao: CategoryDao

    init {
        categoryDao = AppDatabase.getDatabase(application).categoryDao()
    }

    fun insert(category: Category) {
        return categoryDao.insert(category)
    }

    fun getCategories(): LiveData<List<Category>> {
        return categoryDao.getAll()

    }



}