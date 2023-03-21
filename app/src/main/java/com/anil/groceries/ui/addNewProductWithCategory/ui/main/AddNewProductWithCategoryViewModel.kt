package com.anil.groceries.ui.addNewProductWithCategory.ui.main

import android.app.Application
import com.anil.groceries.repository.CategoriesRepository
import com.anil.groceries.ui.base.BaseViewModel

class AddNewProductWithCategoryViewModel(application: Application) : BaseViewModel(application) {
    private var catergoriesRepository: CategoriesRepository

    init {
        catergoriesRepository = CategoriesRepository(application)
    }


}