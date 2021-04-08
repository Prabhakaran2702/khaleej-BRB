package com.prabhakaran.bookstore.data.remote

import com.prabhakaran.bookstore.model.Product
import retrofit2.http.GET

interface ShopAPI {

    @GET("products")
    suspend fun getPosts(): List<Product>

}