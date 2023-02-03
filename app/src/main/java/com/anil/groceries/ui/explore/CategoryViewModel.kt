package com.anil.groceries.ui.explore

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.Category
import com.anil.groceries.repository.CatergoriesRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CategoryViewModel(application: Application) : BaseViewModel(application) {
    private var categoriesRepository: CatergoriesRepository

    var categories: LiveData<List<Category>> = MutableLiveData()

    init {
        categoriesRepository = CatergoriesRepository(application)
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
    }


}