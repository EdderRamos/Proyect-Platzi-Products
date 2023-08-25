package com.webcontrol.platzi.domain

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
           // val result = repository.getAllListProductFromApi()
            /*
            result.data?.let { products ->
                if (products.isNotEmpty()) {
                    repository.clearAllProducts()
                    repository.insertALlProducts(products.map { it.toDataBase() })
                    emit(Resource.Success(products))
                } else {
                    val productsFromDb = repository.getAllListProductFromDataBase()
                    emit(Resource.Success(productsFromDb))
                }
            }?: kotlin.run { emit(result) }

             */
            val json = """[   {
        "id": 274,
        "title": "New Product 2",
        "price": 10,
        "description": "A description",
        "images": [
        "https://picsum.photos/640/640?r=7938"
        ],
        "creationAt": "2023-08-24T14:44:37.000Z",
        "updatedAt": "2023-08-24T14:44:37.000Z",
        "category": {
            "id": 18,
            "name": "hola",
            "image": "https://placeimg.com/640/480/any",
            "creationAt": "2023-08-24T14:44:21.000Z",
            "updatedAt": "2023-08-24T14:44:21.000Z"
        }
    },
    {
        "id": 275,
        "title": "New Product 2",
        "price": 10,
        "description": "A description",
        "images": [
        "https://picsum.photos/640/640?r=7938"
        ],
        "creationAt": "2023-08-24T14:44:40.000Z",
        "updatedAt": "2023-08-24T14:44:40.000Z",
        "category": {
            "id": 18,
            "name": "hola",
            "image": "https://placeimg.com/640/480/any",
            "creationAt": "2023-08-24T14:44:21.000Z",
            "updatedAt": "2023-08-24T14:44:21.000Z"
        }
    },
    {
        "id": 276,
        "title": "New Product 2",
        "price": 10,
        "description": "A description",
        "images": [
            "https://picsum.photos/640/640?r=2483"
        ],
        "creationAt": "2023-08-24T14:44:41.000Z",
        "updatedAt": "2023-08-24T14:44:41.000Z",
        "category": {
            "id": 18,
            "name": "hola",
            "image": "https://placeimg.com/640/480/any",
            "creationAt": "2023-08-24T14:44:21.000Z",
            "updatedAt": "2023-08-24T14:44:21.000Z"
        }
    },
    {
        "id": 277,
        "title": "New Product 2",
        "price": 10,
        "description": "A description",
        "images": [
        "https://picsum.photos/640/640?r=2447"
        ],
        "creationAt": "2023-08-24T14:44:42.000Z",
        "updatedAt": "2023-08-24T14:44:42.000Z",
        "category": {
            "id": 18,
            "name": "hola",
            "image": "https://placeimg.com/640/480/any",
            "creationAt": "2023-08-24T14:44:21.000Z",
            "updatedAt": "2023-08-24T14:44:21.000Z"
        }
    },
    {
        "id": 278,
        "title": "New Product 2",
        "price": 10,
        "description": "A description",
        "images": [
            "https://picsum.photos/640/640?r=620"
        ],
        "creationAt": "2023-08-24T14:44:43.000Z",
        "updatedAt": "2023-08-24T14:44:43.000Z",
        "category": {
            "id": 18,
            "name": "hola",
            "image": "https://placeimg.com/640/480/any",
            "creationAt": "2023-08-24T14:44:21.000Z",
            "updatedAt": "2023-08-24T14:44:21.000Z"
        }
    }]"""

            val gson = Gson()

            val productList: List<ProductModel> =
                gson.fromJson(json, object : TypeToken<List<ProductModel>>() {}.type)
            emit(Resource.Success(productList))

        } catch (e: HttpException) {
            Log.i(tag, e.localizedMessage!!)
            emit(Resource.Error(e.localizedMessage ?: Constant.MSG_ERROR_INTERN))
        } catch (e: IOException) {
            Log.i(tag, e.localizedMessage!!)
            emit(Resource.Error(Constant.API_MSG_ERROR))
        }
    }
}