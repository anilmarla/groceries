package com.anil.groceries.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.anil.groceries.database.AppDatabase
import com.anil.groceries.database.dao.CategoryDao
import com.anil.groceries.model.Category
import com.anil.groceries.model.Post
import com.anil.groceries.network.Api
import com.anil.groceries.network.ApiService
import com.anil.groceries.network.Result
import com.anil.groceries.network.succeeded
import com.anil.groceries.ui.base.BaseRepository

class CategoriesRepository(application: Application) : BaseRepository() {

    private val categoryDao: CategoryDao
    private var apiService: ApiService

    init {
        categoryDao = AppDatabase.getDatabase(application).categoryDao()
        apiService = Api.getInstance(application.applicationContext).apiService
    }

    fun insert(category: Category) {
        return categoryDao.insert(category)
    }

    fun getCategories(): LiveData<List<Category>> {
        return categoryDao.getAll()
    }

    suspend fun getPosts(): Result<List<Post>> {
        val result =  getResult { apiService.getPosts() }

        if(result.succeeded){
            // insert into db
            // categoryDao.insert()
        }

        return result
    }
}