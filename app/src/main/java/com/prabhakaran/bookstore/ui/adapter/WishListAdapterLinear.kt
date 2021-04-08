package com.prabhakaran.bookstore.ui.adapter

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prabhakaran.bookstore.R
import com.prabhakaran.bookstore.constant.Constant
import com.prabhakaran.bookstore.model.Product
import com.prabhakaran.bookstore.model.WishList
import com.prabhakaran.bookstore.ui.activity.BookDetailsActivity
import com.prabhakaran.bookstore.utils.ObjectSerializer
import com.squareup.picasso.Picasso
import java.lang.String

class WishListAdapterLinear(private val userList: List<WishList>, private val context: Context, val clickListener: (WishList, Int) -> Unit): RecyclerView.Adapter<WishListAdapterLinear.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_wish_list, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        userList[position].article?.let { holder.bindItems(userList[position],it,context,clickListener) }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(wishList: WishList,product: Product, context: Context, clickListener: (WishList, Int) -> Unit) {
            val tvWishItemName = itemView.findViewById(R.id.tvWishItemName) as TextView
            val tv_rating = itemView.findViewById(R.id.tv_rating) as TextView
            val tvWishPrice = itemView.findViewById(R.id.tvWishPrice) as TextView
            val tvremove = itemView.findViewById(R.id.tv_remove) as TextView
            val imageWishListItem = itemView.findViewById(R.id.imageWishListItem) as ImageView


            tvWishItemName.text = product.name
            tvWishPrice.text =  Constant.CURRENCY + String.valueOf(
                    product.price)

            tv_rating.text = product.review

            if (product.imageName.isNullOrEmpty()) {
                imageWishListItem.setImageResource(R.drawable.ic_launcher_background)
            } else {

                Picasso.get()
                        .load(product.imageName)
                        .fit()
                        .placeholder(R.color.white)
                        .centerCrop()
                        .into(imageWishListItem)
            }

            itemView.setOnClickListener {

                val intent = Intent(context, BookDetailsActivity::class.java)
                intent.putExtra("book", ObjectSerializer.serialize(product))
                context.startActivity(intent)

            }

            tvremove.setOnClickListener  { clickListener(wishList, position) }


        }
    }
}
