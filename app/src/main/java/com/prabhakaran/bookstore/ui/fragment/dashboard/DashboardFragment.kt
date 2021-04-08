package com.prabhakaran.bookstore.ui.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prabhakaran.bookstore.ui.adapter.ProductAdapter
import com.prabhakaran.bookstore.model.Product
import com.google.android.material.snackbar.Snackbar
import com.prabhakaran.bookstore.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var binding : FragmentDashboardBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
       // val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

         binding = FragmentDashboardBinding.inflate(layoutInflater)

        dashboardViewModel.productList.observe(viewLifecycleOwner, Observer {

            val adapter =
                context?.let { it1 ->
                    ProductAdapter(it,it1) { product: Product, i: Int ->
                        showSnack(product.name)
                    }

                }

            binding.lvProducts.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            binding.lvProducts.adapter = adapter

            binding.lvProducts4.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            binding.lvProducts4.adapter = adapter

        })

        dashboardViewModel.productListIndian.observe(viewLifecycleOwner, Observer {

            val adapter =
                context?.let { it1 ->
                    ProductAdapter(it,it1) { product: Product, i: Int ->
                        showSnack(product.name)
                    }

                }


            binding.lvProducts2.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            binding.lvProducts2.adapter = adapter



        })

        dashboardViewModel.productListCricket.observe(viewLifecycleOwner, Observer {

            val adapter =
                context?.let { it1 ->
                    ProductAdapter(it,it1) { product: Product, i: Int ->
                        showSnack(product.name)
                    }

                }


            binding.lvProducts3.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            binding.lvProducts3.adapter = adapter



        })


        dashboardViewModel.loadData()

        return binding.root
    }

    fun showSnack(name : String){
         val snackbar1 = Snackbar.make(binding.root, "$name added to your cart.", Snackbar.LENGTH_SHORT)
            snackbar1.show()

    }


    }

