package com.prabhakaran.bookstore.di

import android.app.Application
import com.prabhakaran.bookstore.data.local.doa.CartItemDao
import com.prabhakaran.bookstore.data.local.doa.WishListDao
import com.prabhakaran.bookstore.data.remote.ShopAPI
import com.prabhakaran.bookstore.data.repository.CartRepository
import com.prabhakaran.bookstore.data.repository.ProductRepository
import com.prabhakaran.bookstore.data.repository.WishListRepository

import org.koin.dsl.module

val repositoryModule = module {
    fun provideProductRepository(application: Application,api: ShopAPI): ProductRepository {
        return ProductRepository(application,api)
    }

    fun provideCartRepository(dao: CartItemDao): CartRepository {
        return CartRepository(dao)
    }

    fun provideWishListRepository(dao: WishListDao): WishListRepository {
        return WishListRepository(dao)
    }


    single { provideProductRepository(get(),get()) }
    single { provideCartRepository(get()) }
    single { provideWishListRepository(get()) }


}