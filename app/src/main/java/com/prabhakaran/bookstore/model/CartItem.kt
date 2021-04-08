package com.prabhakaran.bookstore.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class CartItem(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "cart_id")
        val id: Int,
        @Embedded val product: Product?) {
}

