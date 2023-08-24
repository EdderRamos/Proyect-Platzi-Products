package com.webcontrol.platzi.data.network

import android.util.Log
import com.webcontrol.platzi.core.Constant
import com.webcontrol.platzi.core.Resource
import com.webcontrol.platzi.data.network.response.ProductResponse
import javax.inject.Inject

class MainService @Inject constructor(
    private val response: MainClient,
) {
    private val tag = "MainService"
    suspend fun getProducts():Resource<List<ProductResponse>>{
        val response = response.getProducts()
        Log.i(tag , response.toString())
        when (response.code()) {
            200 -> {
                response.body()?.let { result ->
                    Log.i(tag , result.toString())
                    return Resource.Success(result)
                }
            }
        }
        return Resource.Error(Constant.MSG_ERROR_INTERN)

    }
}