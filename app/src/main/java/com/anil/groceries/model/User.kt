package com.anil.groceries.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "users")
@Parcelize
data class User(
    @PrimaryKey
    var id: String,
    var name: String,
    var email: String,
    var password: String,
    var isLoggedIn: Boolean = false,
    var profilePicture: String? = null
) : Parcelable
