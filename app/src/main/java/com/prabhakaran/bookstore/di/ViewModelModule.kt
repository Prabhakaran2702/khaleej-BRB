package com.prabhakaran.bookstore.di

import com.prabhakaran.bookstore.ui.activity.BookDetailViewModel
import com.prabhakaran.bookstore.ui.fragment.cart.CartViewModel
import com.prabhakaran.bookstore.ui.fragment.dashboard.DashboardViewModel
import com.prabhakaran.bookstore.ui.fragment.orderplacing.OrderPlacingViewModel
import com.prabhakaran.bookstore.ui.fragment.wishlist.WishListViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { DashboardViewModel() }
    single { CartViewModel(get(),get())  }
    single { OrderPlacingViewModel(get(),get())  }
    single { BookDetailViewModel(get(),get(),get())  }
    single { WishListViewModel(get(),get())  }

}