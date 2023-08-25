package com.webcontrol.platzi.domain.model

import com.webcontrol.platzi.data.database.entities.ProductEntity
import com.webcontrol.platzi.data.network.response.ProductResponse
import com.webcontrol.platzi.core.ui.ListAdapterItem

data class ProductModel(
    val category: CategoryModel,
    val creationAt: String,
    val description: String,
    override val id: Long,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
) : ListAdapterItem {

}

data class CategoryModel(
    val creationAt: String,
    val id: Int,
    val image: String,
    val name: String,
    val updatedAt: String
)

fun ProductResponse.toDomain() = ProductModel(
    category = CategoryModel(
        creationAt = category.creationAt,
        id = category.id,
        image = category.image,
        name = category.name,
        updatedAt = category.updatedAt
    ),
    creationAt = creationAt,
    description = description,
    id = id.toLong(),
    images = images,
    price = price,
    title = title,
    updatedAt = updatedAt
)

fun ProductEntity.toDomain() = ProductModel(
    category = CategoryModel(
        creationAt = category.creationAt,
        id = category.id,
        image = category.image,
        name = category.name,
        updatedAt = category.updatedAt
    ),
    creationAt = creationAt,
    description = description,
    id = id.toLong(),
    images = images,
    price = price,
    title = title,
    updatedAt = updatedAt
)
