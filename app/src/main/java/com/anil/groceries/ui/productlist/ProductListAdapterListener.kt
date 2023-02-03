package com.anil.groceries.ui.productlist

import com.anil.groceries.model.Product

interface ProductListAdapterListener {
    fun onIconClicked(product: Product)
    fun onAddBtnClicked(product: Product)

}
