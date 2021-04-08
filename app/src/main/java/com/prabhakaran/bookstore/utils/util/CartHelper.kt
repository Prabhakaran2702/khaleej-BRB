package com.prabhakaran.bookstore.utils.util

import com.prabhakaran.bookstore.utils.model.Cart

object CartHelper {
    /**
     * Retrieve the shopping cart. Call this before perform any manipulation on the shopping cart.
     *
     * @return the shopping cart
     */
    var cart: Cart? = Cart()
        get() {
            if (field == null) {
                field = Cart()
            }
            return field
        }
        private set
}