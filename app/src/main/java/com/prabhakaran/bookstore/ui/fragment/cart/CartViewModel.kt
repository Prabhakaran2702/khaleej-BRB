package com.prabhakaran.bookstore.ui.fragment.cart

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prabhakaran.bookstore.BaseViewModel
import com.prabhakaran.bookstore.constant.Constant
import com.prabhakaran.bookstore.data.repository.CartRepository
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class CartViewModel(application: Application, private val repository: CartRepository) : BaseViewModel(application) {


    private val _cartItems = MutableLiveData<ArrayList<Product>>()
    var cartItems: LiveData<List<CartItem>> = repository.cartItems


    fun loadData(){
        _cartItems.value = Constant.PRODUCT_LIST
    }



    fun deleteAll(){

        launch {
            repository.deleteAllProducts()
        }
    }


    fun deleteProduct(product: CartItem) {


        CoroutineScope(Dispatchers.Main).launch {
            repository.deleteProduct(product)
        }


    }

}