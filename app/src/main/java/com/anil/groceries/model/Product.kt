package com.anil.groceries.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "products")
@Parcelize
data class Product(
    @PrimaryKey var id: String,
    var name: String,
    var price: Int,
    var image: String,
    var categoryId: String,
    var isAddedCart: Boolean =false
) : Parcelable
