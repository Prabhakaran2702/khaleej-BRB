package com.prabhakaran.bookstore.ui.fragment.cart

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prabhakaran.bookstore.R
import com.prabhakaran.bookstore.databinding.FragmentNotificationsBinding

import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.Product
import com.prabhakaran.bookstore.ui.adapter.CartItemAdapterLinear
import com.prabhakaran.bookstore.ui.adapter.ProductAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModel<CartViewModel>()

    private lateinit var  binding : FragmentNotificationsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

    //    val root = inflater.inflate(R.layout.fragment_notifications, container, false)
         binding = FragmentNotificationsBinding.inflate(layoutInflater)

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)

        if(navBar.visibility!= View.VISIBLE){
            navBar.visibility=View.VISIBLE
        }

        viewModel.cartItems.observe(viewLifecycleOwner, Observer {


                val adapter = context?.let { context ->

                    CartItemAdapterLinear(true,it,context) { product: CartItem, i: Int ->
                        showAlert(product)
                    }
                }

                binding.cartList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                binding.cartList.adapter = adapter

            calculatePrice(it)



        })

        viewModel.loadData()

      // viewModel.deleteAll()

        binding.continueButton.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_navigation_notifications_to_navigation_ordersummary)

        }

        return binding.root
    }

  private fun showAlert(product: CartItem){

      val builder = AlertDialog.Builder(context)
      builder.setTitle("Are you sure you want remove this item")

      builder.setPositiveButton("Yes") { dialog, which ->

          viewModel.deleteProduct(product)

      }
      builder.show()

    }

    private fun calculatePrice(list : List<CartItem>){

        var total =0.0

        list.forEach {
            total+= it.product?.price ?: 0.0
        }
        binding.tvPrice.text = "AED $total"


        if(total==0.0){

            binding.linearLayout.visibility = View.GONE
            binding.continueButton.visibility = View.GONE
            binding.animCartEmpty.visibility = View.VISIBLE
            binding.tvCartEmpty.visibility = View.VISIBLE

        }else{

            binding.linearLayout.visibility = View.VISIBLE
            binding.continueButton.visibility = View.VISIBLE
            binding.animCartEmpty.visibility = View.GONE
            binding.tvCartEmpty.visibility = View.GONE

        }


    }


}