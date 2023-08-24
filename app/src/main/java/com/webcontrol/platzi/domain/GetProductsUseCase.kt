package com.webcontrol.platzi.domain

import android.util.Log
import com.webcontrol.platzi.core.Constant
import com.webcontrol.platzi.core.Resource
import com.webcontrol.platzi.data.MainRepository
import com.webcontrol.platzi.data.database.entities.toDataBase
import com.webcontrol.platzi.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    private val tag = "GetProductsUseCase"
    operator fun invoke(): Flow<Resource<List<ProductModel>>> = flow {
        emit(Resource.Loading())
        try {
            val result = repository.getAllListProductFromApi()
            result.data?.let { products ->
                if (products.isNotEmpty()) {
                    repository.clearAllProducts()
                    repository.insertALlProducts(products.map { it.toDataBase() })
                    emit(Resource.Success(products))
                } else {
                   val productsFromDb = repository.getAllListProductFromDataBase()
                    emit(Resource.Success(productsFromDb))
                }
            }
        } catch (e: HttpException) {
            Log.i(tag, e.localizedMessage!!)
            emit(Resource.Error(e.localizedMessage ?: Constant.MSG_ERROR_INTERN))
        } catch (e: IOException) {
            Log.i(tag, e.localizedMessage!!)
            emit(Resource.Error(Constant.API_MSG_ERROR))
        }
    }
}