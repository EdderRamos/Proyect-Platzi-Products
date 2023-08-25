package com.webcontrol.platzi.domain

import com.webcontrol.platzi.data.MainRepository
import com.webcontrol.platzi.domain.model.ProductModel
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val repository: MainRepository
) {

    suspend operator fun invoke(query: String): List<ProductModel> {
        val products = repository.getAllListProductFromDataBase()
        val filteredList = products.filter { product ->
            product.title.contains(query, ignoreCase = true)
        }
        return filteredList
    }
}