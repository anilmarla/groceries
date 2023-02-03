package com.anil.groceries.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "categories")
@Parcelize
data class Category(
    @PrimaryKey val id: String,
    var name: String,
    var image: String,
    var backgroundColor: Int,
    var borderColor: Int,
) : Parcelable
