package com.prabhakaran.bookstore.ui.fragment.wishlist

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prabhakaran.bookstore.R
import com.prabhakaran.bookstore.constant.Constant
import com.prabhakaran.bookstore.databinding.ProfileFragmentBinding
import com.prabhakaran.bookstore.databinding.WishListFragmentBinding
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.WishList
import com.prabhakaran.bookstore.ui.adapter.CartItemAdapterLinear
import com.prabhakaran.bookstore.ui.adapter.WishListAdapterLinear
import com.prabhakaran.bookstore.ui.fragment.cart.CartViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class WishListFragment : Fragment() {

    private val viewModel: WishListViewModel by viewModel<WishListViewModel>()

    private lateinit var binding: WishListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WishListFragmentBinding.inflate(layoutInflater)


        viewModel.wishItems.observe(viewLifecycleOwner, Observer {
            val adapter = context?.let {context ->
                WishListAdapterLinear(
                   it,
                        context
                ){ wishList:WishList, i: Int ->
                    showAlert(wishList)
                }


            }

            binding.wishList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            binding.wishList.adapter = adapter


            cartEmpty(it)

        })


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    private fun cartEmpty(it:List<WishList>){

        if(it.isEmpty()){
            binding.tvCartEmpty.visibility = View.VISIBLE
        }

        else{
            binding.tvCartEmpty.visibility = View.GONE
        }

    }


    private fun showAlert(wishList: WishList){

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure you want remove this item")

        builder.setPositiveButton("Yes") { dialog, which ->

            viewModel.deleteProduct(wishList)

        }
        builder.show()

    }


}