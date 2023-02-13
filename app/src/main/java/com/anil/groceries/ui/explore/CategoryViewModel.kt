package com.anil.groceries.ui.explore

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.Category
import com.anil.groceries.model.Post
import com.anil.groceries.network.Result
import com.anil.groceries.repository.CategoriesRepository
import com.anil.groceries.ui.base.BaseViewModel
import com.anil.groceries.ui.base.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CategoryViewModel(application: Application) : BaseViewModel(application) {
    private var categoriesRepository: CategoriesRepository

    var categories: LiveData<List<Category>> = MutableLiveData()

    private val _posts = SingleLiveEvent<Result<List<Post>>>()
    val posts: LiveData<Result<List<Post>>>
        get() = _posts

    init {
        categoriesRepository = CategoriesRepository(application)
        categories = categoriesRepository.getCategories()
    }

    fun addCategory(name: String, image: String, backgroundColor: Int, borderColor: Int) {
        val category = Category(
            id = UUID.randomUUID().toString(),
            name = name,
            image = image,
            backgroundColor = backgroundColor,
            borderColor = borderColor
        )
        viewModelScope.launch(Dispatchers.IO) {
            categoriesRepository.insert(category)

        }
        viewModelScope.launch(Dispatchers.IO) {
            categoriesRepository.getCategories()
        }
    }

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            // send loading before making http call
            _posts.postValue(Result.Loading)

            // make http call
            _posts.postValue(categoriesRepository.getPosts())
        }
    }
}