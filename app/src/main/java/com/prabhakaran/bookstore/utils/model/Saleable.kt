package com.prabhakaran.bookstore.utils.model

import java.math.BigDecimal

interface Saleable {
    fun getPrice():BigDecimal
    fun getName():String
}