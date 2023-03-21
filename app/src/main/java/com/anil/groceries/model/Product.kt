package com.anil.groceries.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey var id: String,
    var title: String,
    var description: String,
    var thumbnail: String,
    var price: Int,
    var discountPercentage: Double,
    var rating: Double,
    var stock: Int,
    var brand: String,
    var category: String,
    var categoryId: String? = null,
    var isAddedCart: Boolean = false,
    var cartQuantity: Int = 0,
    var isAddedFavourite: Boolean = false,
    var images: List<String>? = null
)
