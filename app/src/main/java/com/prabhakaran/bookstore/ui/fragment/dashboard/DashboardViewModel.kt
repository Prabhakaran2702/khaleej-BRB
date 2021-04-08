package com.prabhakaran.bookstore.ui.fragment.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prabhakaran.bookstore.constant.Constant
import com.prabhakaran.bookstore.model.Product
import java.util.ArrayList

class DashboardViewModel : ViewModel() {


    private val _productList = MutableLiveData<ArrayList<Product>>()

    private val _productListIndian = MutableLiveData<ArrayList<Product>>()

    private val _productListCricket = MutableLiveData<ArrayList<Product>>()


    val productList: LiveData<ArrayList<Product>> = _productList

    val productListIndian: LiveData<ArrayList<Product>> = _productListIndian

    val productListCricket: LiveData<ArrayList<Product>> = _productListCricket


   fun loadData(){
       _productList.value = Constant.PRODUCT_LIST
       _productListIndian.value = Constant.PRODUCT_LIST_INDIAN
       _productListCricket.value = Constant.PRODUCT_LIST_CRICKET
   }




}