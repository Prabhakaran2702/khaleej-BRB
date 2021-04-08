package com.prabhakaran.bookstore.ui.activity

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prabhakaran.bookstore.BaseViewModel
import com.prabhakaran.bookstore.constant.Constant
import com.prabhakaran.bookstore.data.repository.CartRepository
import com.prabhakaran.bookstore.data.repository.WishListRepository
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.Product
import com.prabhakaran.bookstore.model.WishList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class BookDetailViewModel(application: Application, private val repository: CartRepository,private  val repositoryWish: WishListRepository) : BaseViewModel(application) {




    fun addProductCart(cartItem: CartItem){

        CoroutineScope(Dispatchers.Main).launch {
            repository.insertProduct(cartItem)
        }
    }


    fun addProductWish(wishList: WishList){

        CoroutineScope(Dispatchers.Main).launch {
            repositoryWish.insertProduct(wishList)
        }
    }



}



