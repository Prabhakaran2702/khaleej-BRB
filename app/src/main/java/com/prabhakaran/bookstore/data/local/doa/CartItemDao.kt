package com.prabhakaran.bookstore.data.local.doa

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prabhakaran.bookstore.model.CartItem



@Dao
interface CartItemDao {

    @Insert
    suspend fun insertProduct(cartItem: CartItem): Long

    @Delete
    suspend fun deleteProduct(cartItem: CartItem)

    @Query("SELECT * FROM CartItem ORDER BY  cart_id DESC")
    fun getAllProducts(): LiveData<List<CartItem>>

    @Query("DELETE FROM CartItem")
    suspend fun deleteAll()

}