package com.webcontrol.platzi.domain.model

import com.webcontrol.platzi.data.database.entities.ProductEntity
import com.webcontrol.platzi.data.network.response.ProductResponse

data class ProductModel(
    val category: CategoryModel,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
)

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
    id = id,
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
    id = id,
    images = images,
    price = price,
    title = title,
    updatedAt = updatedAt
)
