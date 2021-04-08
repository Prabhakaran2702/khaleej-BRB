package com.prabhakaran.bookstore.ui.fragment.wishlist

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

class WishListViewModel(application: Application, private val repository: WishListRepository) : BaseViewModel(application) {


    private val _cartItems = MutableLiveData<ArrayList<Product>>()
    val cartItems: LiveData<ArrayList<Product>> = _cartItems

    var wishItems: LiveData<List<WishList>> = repository.wishItems

    fun deleteAll(){

        launch {
            repository.deleteAllProducts()
        }

    }


    fun deleteProduct(wishList: WishList) {

        CoroutineScope(Dispatchers.Main).launch {
            repository.deleteProduct(wishList)
        }

    }


}







