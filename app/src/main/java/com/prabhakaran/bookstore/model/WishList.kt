package com.prabhakaran.bookstore.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class WishList(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "wish_id")
        val id: Int,
        @Embedded val article: Product?) {
}
