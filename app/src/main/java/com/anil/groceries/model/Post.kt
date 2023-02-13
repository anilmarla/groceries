package com.anil.groceries.model

data class Post(
    val id: Int,
    val title: String? = null,
    val body: String? = null,
    val userId: Int
)
