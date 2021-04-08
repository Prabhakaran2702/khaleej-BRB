package com.prabhakaran.bookstore.di


import com.prabhakaran.bookstore.data.remote.ShopAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideShopAPI(retrofit: Retrofit): ShopAPI {
        return retrofit.create(ShopAPI::class.java)
    }

    single { provideShopAPI(get()) }

}