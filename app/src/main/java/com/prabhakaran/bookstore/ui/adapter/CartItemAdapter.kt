package com.prabhakaran.bookstore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prabhakaran.bookstore.constant.Constant
import com.prabhakaran.bookstore.model.Product
import com.google.android.material.snackbar.Snackbar
import com.prabhakaran.bookstore.R
import java.lang.String
import java.util.ArrayList

class CartItemAdapter(private val userList: ArrayList<Product>, private val context: Context) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position],context)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(product: Product, context: Context) {
            val tvCartItemName = itemView.findViewById(R.id.tvCartItemName) as TextView
            val tvCartItemUnitPrice = itemView.findViewById(R.id.tvCartItemUnitPrice) as TextView
            val tvCartItemImage = itemView.findViewById(R.id.tvCartItemImage) as ImageView

            val ivCartItemAdd = itemView.findViewById(R.id.btn_item_add) as ImageView
            val ivCartItemRemove = itemView.findViewById(R.id.btn_item_minus) as ImageView
            val ivCartItemQty = itemView.findViewById(R.id.btn_item_qty) as TextView

            tvCartItemName.text = product.name
            tvCartItemUnitPrice.text =  Constant.CURRENCY + String.valueOf(
                product.price)
            tvCartItemImage.setImageResource(
                context.resources.getIdentifier(
                    product.imageName, "drawable", context.packageName
                )
            )

            ivCartItemAdd.setOnClickListener {

                val snackbar1 = Snackbar.make(it,product.name+" 1 qty is added." , Snackbar.LENGTH_SHORT)
                snackbar1.show()

            }

            ivCartItemRemove.setOnClickListener {

                val snackbar1 = Snackbar.make(it,product.name+" 1 qty is removed." , Snackbar.LENGTH_SHORT)
                snackbar1.show()

            }



        }
    }
}
