package com.anil.groceries.ui.addCategory

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.Category
import com.anil.groceries.repository.CategoriesRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddCategoryViewModel(application: Application) : BaseViewModel(application) {
    private var catergoriesRepository: CategoriesRepository

    init {
        catergoriesRepository = CategoriesRepository(application)
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
            catergoriesRepository.insert(category)
        }
    }


}