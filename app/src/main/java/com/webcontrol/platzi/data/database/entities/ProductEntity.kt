package com.webcontrol.platzi.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.webcontrol.platzi.domain.model.ProductModel

@Entity(tableName = "products_table")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "category")
    val category: CategoryEntity,
    @ColumnInfo(name = "createdAd")
    val creationAt: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "images")
    val images: List<String>,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "updateAt")
    val updatedAt: String

)

@Entity(tableName = "categories_table")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "creationAt")
    val creationAt: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "updateAt")
    val updatedAt: String
)

fun ProductModel.toDataBase(): ProductEntity {
    return ProductEntity(
        id = id.toInt(),
        category = CategoryEntity(
            id = category.id,
            creationAt = category.creationAt,
            image = category.image,
            name = category.name,
            updatedAt = category.updatedAt
        ),
        creationAt = creationAt,
        description = description,
        images = images,
        price = price,
        title = title,
        updatedAt = updatedAt
    )
}
