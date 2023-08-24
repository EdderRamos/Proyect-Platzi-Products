package com.webcontrol.platzi.data

import com.webcontrol.platzi.core.Resource
import com.webcontrol.platzi.data.database.dao.ProductDao
import com.webcontrol.platzi.data.database.entities.ProductEntity
import com.webcontrol.platzi.data.network.MainService
import com.webcontrol.platzi.domain.model.ProductModel
import com.webcontrol.platzi.domain.model.toDomain
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: MainService,
    private val db: ProductDao
) {
    suspend fun getAllListProductFromApi(): Resource<List<ProductModel>> {
        return when (val result = api.getProducts()) {
            is Resource.Error -> Resource.Error(result.message!!)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> {
                Resource.Success(data = result.data!!.map { it.toDomain() })
            }
        }
    }

    suspend fun getAllListProductFromDataBase(): List<ProductModel> {
        val response: List<ProductEntity> = db.getAllProducts()
        return response.map { it.toDomain() }
    }

    suspend fun insertALlProducts(products: List<ProductEntity>) = db.insertALl(products)

    suspend fun clearAllProducts() = db.deleteAll()
}