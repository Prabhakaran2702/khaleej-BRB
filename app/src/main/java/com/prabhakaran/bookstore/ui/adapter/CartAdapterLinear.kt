package com.prabhakaran.bookstore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prabhakaran.bookstore.R
import com.prabhakaran.bookstore.constant.Constant
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.Product
import com.squareup.picasso.Picasso
import java.lang.String

class CartItemAdapterLinear(private val showRemove: Boolean, private val userList: List<CartItem>, private val context: Context, val clickListener: (CartItem, Int) -> Unit) : RecyclerView.Adapter<CartItemAdapterLinear.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cart_linear, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        userList[position].product?.let { holder.bindItems(showRemove,userList[position],it,context,clickListener) }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(showRemove:Boolean,
            product1: CartItem,
            product: Product,
            context: Context,
            clickListener: (CartItem, Int) -> Unit
        ) {
            val tvProductName = itemView.findViewById(R.id.tvCartItemName) as TextView
            val tvProductPrice = itemView.findViewById(R.id.tvCartItemUnitPrice) as TextView
            val ivProductImage = itemView.findViewById(R.id.tvCartItemImage) as ImageView

            val tv_remove = itemView.findViewById(R.id.tv_remove) as TextView

            tvProductName.text = product.name
            tvProductPrice.text =  Constant.CURRENCY + String.valueOf(
                product.price)

            if (product.imageName.isNullOrEmpty()) {
                ivProductImage.setImageResource(R.drawable.ic_launcher_background)
            } else {

                Picasso.get()
                        .load(product.imageName)
                        .fit()
                        .placeholder(R.color.white)
                        .centerCrop()
                        .into(ivProductImage)
            }

            if(showRemove){tv_remove.visibility = View.VISIBLE}
            else{tv_remove.visibility = View.INVISIBLE}

            tv_remove.setOnClickListener  { clickListener(product1, position) }

        }
    }
}
