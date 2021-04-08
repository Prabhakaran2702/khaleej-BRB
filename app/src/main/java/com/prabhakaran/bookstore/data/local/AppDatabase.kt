package com.prabhakaran.bookstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prabhakaran.bookstore.data.local.doa.CartItemDao
import com.prabhakaran.bookstore.data.local.doa.WishListDao
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.WishList

@Database(entities = [CartItem::class,WishList::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val cartItemDao: CartItemDao
    abstract val wishListDao: WishListDao

}