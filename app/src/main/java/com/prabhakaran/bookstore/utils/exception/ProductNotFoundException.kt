package com.prabhakaran.bookstore.utils.exception

class ProductNotFoundException : RuntimeException {
    constructor() : super(DEFAULT_MESSAGE) {}
    constructor(message: String?) : super(message) {}

    companion object {
        private const val serialVersionUID = 43L
        private const val DEFAULT_MESSAGE = "Product is not found in the shopping cart."
    }
}