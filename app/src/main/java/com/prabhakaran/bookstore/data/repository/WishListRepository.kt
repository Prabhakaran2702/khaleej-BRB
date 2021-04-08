package com.prabhakaran.bookstore.data.repository

import com.prabhakaran.bookstore.data.local.doa.CartItemDao
import com.prabhakaran.bookstore.data.local.doa.WishListDao
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.WishList

class WishListRepository(private val wishListDao: WishListDao) {


    val wishItems = wishListDao.getAllProducts()

    suspend fun insertProduct(wishList: WishList){
        wishListDao.insertProduct(wishList)
    }

    suspend fun deleteProduct(wishList: WishList){
        wishListDao.deleteProduct(wishList)
    }

    suspend fun deleteAllProducts(){
        wishListDao.deleteAll()
    }

}