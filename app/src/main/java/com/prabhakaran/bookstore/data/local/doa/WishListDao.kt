package com.prabhakaran.bookstore.data.local.doa
import androidx.lifecycle.LiveData
import androidx.room.*
import com.prabhakaran.bookstore.model.CartItem
import com.prabhakaran.bookstore.model.WishList


@Dao
interface WishListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(wishList: WishList): Long

    @Delete
    suspend fun deleteProduct(wishList: WishList)

    @Query("SELECT * FROM WishList ORDER BY  wish_id DESC")
    fun getAllProducts(): LiveData<List<WishList>>

    @Query("DELETE FROM WishList")
    suspend fun deleteAll()

}