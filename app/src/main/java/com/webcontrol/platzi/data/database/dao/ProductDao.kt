package com.webcontrol.platzi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.webcontrol.platzi.data.database.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM  products_table")
    suspend fun getAllProducts(): List<ProductEntity>

    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM products_table WHERE id = :productId")
    suspend fun getProduct(productId: Int): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertALl(quote: List<ProductEntity>)

    @Query("DELETE FROM products_table")
    suspend fun deleteAll()

}