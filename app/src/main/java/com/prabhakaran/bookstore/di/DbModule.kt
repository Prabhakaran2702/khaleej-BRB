package com.prabhakaran.bookstore.di

import android.app.Application
import androidx.room.Room
import com.prabhakaran.bookstore.data.local.AppDatabase
import com.prabhakaran.bookstore.data.local.doa.CartItemDao
import com.prabhakaran.bookstore.data.local.doa.WishListDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "eds.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideCartItemDao(database: AppDatabase): CartItemDao {
        return database.cartItemDao
    }

    fun provideWishListDao(database: AppDatabase): WishListDao {
        return database.wishListDao
    }



    single { provideAppDatabase(androidApplication()) }
    single { provideCartItemDao(get()) }
    single { provideWishListDao(get()) }

}