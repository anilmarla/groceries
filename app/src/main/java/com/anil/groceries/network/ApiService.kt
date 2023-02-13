package com.anil.groceries.network

import com.anil.groceries.model.Post
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}