package com.anil.groceries.ui.add_category

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.groceries.model.Category
import com.anil.groceries.model.User
import com.anil.groceries.repository.CatergoriesRepository
import com.anil.groceries.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddCategoryViewModel(application: Application) : BaseViewModel(application) {
    private var catergoriesRepository: CatergoriesRepository

    init {
        catergoriesRepository = CatergoriesRepository(application)
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