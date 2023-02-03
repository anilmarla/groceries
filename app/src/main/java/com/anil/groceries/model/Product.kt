package com.anil.groceries.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey var id: String,
    var name: String,
    var price: Int,
    var image: String,
    var categoryId: String,
    var isAddedCart: Boolean = false,
    var cartQuantity: Int = 0,
    var isAddedFavourite: Boolean = false
)
