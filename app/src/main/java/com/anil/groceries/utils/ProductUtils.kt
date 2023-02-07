package com.anil.groceries.utils

import android.content.Context
import com.anil.groceries.R
import com.anil.groceries.model.Product

class ProductUtils {
    companion object {
        fun addProductToCart(product: Product): Product {
            product.isAddedCart = true
            product.cartQuantity = product.cartQuantity + 1
            return product
        }

        fun minusProduct(product: Product): Product {

            if (product.cartQuantity > 1) {
                product.cartQuantity = product.cartQuantity - 1
            } else {
                product.isAddedCart = false
                product.cartQuantity = 0
            }
            return product
        }

        fun formatPrice(context: Context?, amount: Int): String? {
            return context?.getString(R.string.rupee, amount)
        }

        fun addFavouriteToCart(product: Product): Product {
            product.isAddedFavourite = !product.isAddedFavourite
            return product
        }
    }
}