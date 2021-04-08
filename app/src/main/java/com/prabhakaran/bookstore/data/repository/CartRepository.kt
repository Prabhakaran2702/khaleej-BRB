package com.prabhakaran.bookstore.data.repository

import com.prabhakaran.bookstore.data.local.doa.CartItemDao
import com.prabhakaran.bookstore.model.CartItem

class CartRepository(private val cartItemDao: CartItemDao) {


    val cartItems = cartItemDao.getAllProducts()

    suspend fun insertProduct(cartItem: CartItem){
        cartItemDao.insertProduct(cartItem)
    }

    suspend fun deleteProduct(cartItem: CartItem){
        cartItemDao.deleteProduct(cartItem)
    }

    suspend fun deleteAllProducts(){
        cartItemDao.deleteAll()
    }

}