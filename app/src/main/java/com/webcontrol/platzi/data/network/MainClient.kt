package com.webcontrol.platzi.data.network

import com.webcontrol.platzi.data.network.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface MainClient {
    @GET("api/v1/products")
    suspend fun getProducts(): Response<List<ProductResponse>>
}