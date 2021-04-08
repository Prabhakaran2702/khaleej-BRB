package com.prabhakaran.bookstore.ui.activity

import android.content.Intent

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.prabhakaran.bookstore.databinding.ActivityBookDetailsBinding
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.Product
import com.prabhakaran.bookstore.model.WishList
import com.prabhakaran.bookstore.utils.ObjectSerializer
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel


class BookDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookDetailsBinding

    private lateinit var book: Product

    private val viewModel: BookDetailViewModel by viewModel<BookDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        book = ObjectSerializer.deserialize(intent.getStringExtra("book")) as Product

        setLayout()

    }

    private  fun setLayout() {

        // filling every views

        if (book.imageName.isNullOrEmpty()) {

        } else {
            binding.expandedImage.visibility = View.VISIBLE
            Picasso.get()
                .load(book.imageName)
                .fit()
                .centerCrop()
                .into(binding.expandedImage)
        }

              binding.articleTitle.text = book.name
             binding.articleContent.text = book.description
             binding.articlePrice.text = "AED "+ book.price.toString()


            binding.btnBack.setOnClickListener {
                finish()
            }


        binding.imgShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/web"
                intent.putExtra(Intent.EXTRA_TEXT, book.name+" \n"+book.description)
                val shareIntent = Intent.createChooser(intent, "Share via")
                startActivity(shareIntent)
        }



        binding.imagePlayVideo.setOnClickListener {
           showVideo()
        }

        binding.btnAddToCart .setOnClickListener {
            Snackbar.make(it, "${book.name} Added to Cart", Snackbar.LENGTH_SHORT).show()
            viewModel.addProductCart(CartItem(0, book))
        }


        binding.btnWishlist .setOnClickListener {
            Snackbar.make(it, "${book.name} Added to WishList", Snackbar.LENGTH_SHORT).show()
            viewModel.addProductWish(WishList(0, book))
        }

    }


    fun showVideo(){

        val intent = Intent(this, VideoPlayerActivity::class.java)
       startActivity(intent)
        VideoPlayerActivity
    }






}