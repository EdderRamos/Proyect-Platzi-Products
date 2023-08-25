package com.webcontrol.platzi.domain

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.webcontrol.platzi.core.Constant
import com.webcontrol.platzi.core.Resource
import com.webcontrol.platzi.data.MainRepository
import com.webcontrol.platzi.data.database.entities.toDataBase
import com.webcontrol.platzi.domain.model.CategoryModel
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

            //IT WAS COMMENTED BECAUSE THERE ARE PROBLEMS WITH THE SERVER-API

            /*
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
            } ?: kotlin.run { emit(result) }


             */

            //HARDCORE CODE TO SHOW DATA
            repository.clearAllProducts()
            repository.insertALlProducts(getDataHardCoded().map { it.toDataBase() })
           emit(Resource.Success(getDataHardCoded()))

        } catch (e: HttpException) {
            Log.i(tag, e.localizedMessage!!)
            emit(Resource.Error(e.localizedMessage ?: Constant.MSG_ERROR_INTERN))
        } catch (e: IOException) {
            Log.i(tag, e.localizedMessage!!)
            emit(Resource.Error(Constant.API_MSG_ERROR))
        }
    }

    private fun getDataHardCoded(): List<ProductModel> {
        val category1 = CategoryModel(
            creationAt = "2023-08-25",
            id = 1,
            image = "https://b2523400.smushcdn.com/2523400/wp-content/uploads/2020/12/shutterstock_1622904148.jpg?lossy=1&strip=1&webp=1",
            name = "Electronics",
            updatedAt = "2023-08-25"
        )

        val category2 = CategoryModel(
            creationAt = "2023-08-24",
            id = 2,
            image = "https://blog.japanwondertravel.com/wp-content/uploads/2021/10/Japanese-clothing-store.jpg",
            name = "Clothing",
            updatedAt = "2023-08-24"
        )

        val product1 = ProductModel(
            category = category1,
            creationAt = "2023-08-25",
            description = "A high-tech gadget for your needs.",
            id = 101,
            images = listOf("https://www.zdnet.com/a/img/resize/11f22dda41d8422f2d9674a4b57805b762a7eedd/2023/04/24/4e586f53-afa2-452d-baf4-cc7c78c2c5fb/samsung-galaxy-a54-5g.jpg?auto=webp&fit=crop&height=360&width=640", "https://falabella.scene7.com/is/image/FalabellaPE/19692812_1?wid=800&hei=800&qlt=70"),
            price = 599,
            title = "Smartphone",
            updatedAt = "2023-08-25"
        )

        val product2 = ProductModel(
            category = category2,
            creationAt = "2023-08-24",
            description = "Stay fashionable with this trendy attire.",
            id = 102,
            images = listOf("https://assets.hermes.com/is/image/hermesproduct/h-embroidered-t-shirt--072025HA01-worn-5-0-0-800-800_g.jpg", "https://assets.hermes.com/is/image/hermesproduct/h-embroidered-t-shirt--072025HA01-worn-5-0-0-800-800_g.jpg"),
            price = 79,
            title = "T-shirt",
            updatedAt = "2023-08-24"
        )
        val category3 = CategoryModel(
            creationAt = "2023-08-23",
            id = 3,
            image = "https://images.theconversation.com/files/45159/original/rptgtpxd-1396254731.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1356&h=668&fit=crop",
            name = "Books",
            updatedAt = "2023-08-23"
        )

        val category4 = CategoryModel(
            creationAt = "2023-08-22",
            id = 4,
            image = "https://media.istockphoto.com/id/1196974664/es/foto/set-de-electrodom%C3%A9sticos-de-cocina-en-la-habitaci%C3%B3n-en-el-fondo-de-la-pared.jpg?s=612x612&w=0&k=20&c=d7r44Bfcn89FYyVcsUizkSq8m2-4Lr0fqx2rT1aC8Wg=",
            name = "Home appliances",
            updatedAt = "2023-08-22"
        )

        val product3 = ProductModel(
            category = category3,
            creationAt = "2023-08-23",
            description = "A thrilling adventure that will keep you on the edge of your seat.",
            id = 103,
            images = listOf("https://www.rd.com/wp-content/uploads/2021/11/mystery-books-collage-FT2.jpg", "https://www.rd.com/wp-content/uploads/2021/11/mystery-books-collage-FT2.jpg"),
            price = 15,
            title = "Mystery Novel",
            updatedAt = "2023-08-23"
        )

        val product4 = ProductModel(
            category = category4,
            creationAt = "2023-08-22",
            description = "Make your life easier with this modern home appliance.",
            id = 104,
            images = listOf("https://www.androidauthority.com/wp-content/uploads/2021/03/Roborock-S7-robot-vacuum-mop-docked-medium.jpg", "https://media.cnn.com/api/v1/images/stellar/prod/211119121609-underscored-best-robot-vacuums-lead-image.jpg?q=x_0,y_0,h_2268,w_4030,c_fill/w_800"),
            price = 249,
            title = "Robotic Vacuum Cleaner",
            updatedAt = "2023-08-22"
        )

        val product5 = ProductModel(
            category = category1,
            creationAt = "2023-08-21",
            description = "Experience entertainment like never before with this cutting-edge device.",
            id = 105,
            images = listOf("https://coolboxpe.vtexassets.com/arquivos/ids/299069/10000071_1.jpg?v=1779298921", "https://home.ripley.com.pe/Attachment/WOP_5/2018319095073/2018319095073_2.jpg"),
            price = 899,
            title = "Ultra HD Smart TV",
            updatedAt = "2023-08-21"
        )

        val product6 = ProductModel(
            category = category2,
            creationAt = "2023-08-20",
            description = "Stay comfortable and stylish with these premium sneakers.",
            id = 106,
            images = listOf("https://e00-expansion.uecdn.es/assets/multimedia/imagenes/2022/11/25/16693316060500.jpg", "https://brandsego.com/cdn/shop/files/Walk-Women-Sneakers-White-NA14665-Usman-1685023767.jpg?v=1685023769"),
            price = 129,
            title = "Sneakers",
            updatedAt = "2023-08-20"
        )

        return listOf(product1, product2, product3, product4, product5, product6)
    }
}