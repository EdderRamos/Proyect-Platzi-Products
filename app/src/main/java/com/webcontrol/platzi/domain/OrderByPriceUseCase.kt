package com.webcontrol.platzi.domain

import com.webcontrol.platzi.domain.model.ProductModel
import javax.inject.Inject

class OrderByPriceUseCase @Inject constructor() {

    suspend operator fun invoke(
        ascending: Boolean,
        products: List<ProductModel>?
    ): List<ProductModel> {

        val sortedProducts = products.orEmpty().toMutableList()
        sortedProducts.sortBy { it.price }
        if (!ascending) {
            sortedProducts.reverse()
        }
        return sortedProducts
    }
}