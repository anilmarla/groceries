package com.anil.groceries.ui.cart

import com.anil.groceries.model.Product

interface CartListAdapterListener {
    fun onPlusClicked(product: Product)
    fun onRemoveClicked(product: Product)
    fun onMinusClicked(product: Product)
    fun onProductClicked(product: Product)

}
