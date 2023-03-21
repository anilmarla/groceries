package com.anil.groceries.network

import com.anil.groceries.model.Post
import com.anil.groceries.model.Product
import com.anil.groceries.model.ProductResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("products")
    suspend fun getProducts(): Response<ProductResponse>

    @GET("product/{productId}")
    suspend fun getProduct(
        @Path("productId") productId: String
    ): Response<Product>
}