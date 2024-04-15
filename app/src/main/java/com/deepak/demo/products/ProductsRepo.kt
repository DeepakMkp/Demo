package com.deepak.demo.products

import com.deepak.demo.networking.ApiService
import javax.inject.Inject

class ProductsRepo @Inject constructor(private val retrofitService: ApiService) {

    suspend fun getProducts(pageNo: Int): ProductsData {
        return retrofitService.getAllProducts(pageNo)
    }
}