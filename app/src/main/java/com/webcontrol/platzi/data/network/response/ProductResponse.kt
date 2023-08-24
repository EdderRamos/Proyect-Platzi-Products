package com.webcontrol.platzi.data.network.response


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("category")
    val category: CategoryResponse,
    @SerializedName("creationAt")
    val creationAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)

data class CategoryResponse(
    @SerializedName("creationAt")
    val creationAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)