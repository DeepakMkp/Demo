package com.deepak.demo.networking

import com.deepak.demo.products.ProductsData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getAllProducts(@Query("limit") limit: Int) : ProductsData
}