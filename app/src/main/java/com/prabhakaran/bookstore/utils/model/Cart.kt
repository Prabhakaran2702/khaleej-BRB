package com.prabhakaran.bookstore.utils.model


import com.prabhakaran.bookstore.utils.exception.ProductNotFoundException
import com.prabhakaran.bookstore.utils.exception.QuantityOutOfRangeException
import java.io.Serializable
import java.math.BigDecimal
import java.util.*


/**
 * A representation of shopping cart.
 *
 *
 * A shopping cart has a map of [Saleable] products to their corresponding quantities.
 */
class Cart : Serializable {
    private val cartItemMap: MutableMap<Saleable, Int> = HashMap<Saleable, Int>()

    /**
     * Get total price of all products in this shopping cart
     *
     * @return Total price of all products in this shopping cart
     */
    var totalPrice = BigDecimal.ZERO
        private set

    /**
     * Get total quantity of all products in this shopping cart
     *
     * @return Total quantity of all products in this shopping cart
     */
    var totalQuantity = 0
        private set

    /**
     * Add a quantity of a certain [Saleable] product to this shopping cart
     *
     * @param sellable the product will be added to this shopping cart
     * @param quantity the amount to be added
     */
    fun add(sellable: Saleable, quantity: Int) {
        if (cartItemMap.containsKey(sellable)) {
            cartItemMap[sellable] = cartItemMap[sellable]!! + quantity
        } else {
            cartItemMap[sellable] = quantity
        }
        totalPrice =
            totalPrice.add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity.toLong())))
        totalQuantity += quantity
    }

    /**
     * Set new quantity for a [Saleable] product in this shopping cart
     *
     * @param sellable the product which quantity will be updated
     * @param quantity the new quantity will be assigned for the product
     * @throws ProductNotFoundException    if the product is not found in this shopping cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */
    @Throws(ProductNotFoundException::class, QuantityOutOfRangeException::class)
    fun update(sellable: Saleable, quantity: Int) {
        if (!cartItemMap.containsKey(sellable)) throw ProductNotFoundException()
        if (quantity < 0) throw QuantityOutOfRangeException("$quantity is not a valid quantity. It must be non-negative.")
        val productQuantity = cartItemMap[sellable]!!
        val productPrice: BigDecimal =
            sellable.getPrice().multiply(BigDecimal.valueOf(productQuantity.toLong()))
        cartItemMap[sellable] = quantity
        totalQuantity = totalQuantity - productQuantity + quantity
        totalPrice = totalPrice.subtract(productPrice)
            .add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity.toLong())))
    }

    /**
     * Remove a certain quantity of a [Saleable] product from this shopping cart
     *
     * @param sellable the product which will be removed
     * @param quantity the quantity of product which will be removed
     * @throws ProductNotFoundException    if the product is not found in this shopping cart
     * @throws QuantityOutOfRangeException if the quantity is negative or more than the existing quantity of the product in this shopping cart
     */
    @Throws(ProductNotFoundException::class, QuantityOutOfRangeException::class)
    fun remove(sellable: Saleable, quantity: Int) {
        if (!cartItemMap.containsKey(sellable)) throw ProductNotFoundException()
        val productQuantity = cartItemMap[sellable]!!
        if (quantity < 0 || quantity > productQuantity) throw QuantityOutOfRangeException("$quantity is not a valid quantity. It must be non-negative and less than the current quantity of the product in the shopping cart.")
        if (productQuantity == quantity) {
            cartItemMap.remove(sellable)
        } else {
            cartItemMap[sellable] = productQuantity - quantity
        }
        totalPrice =
            totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity.toLong())))
        totalQuantity -= quantity
    }

    /**
     * Remove a [Saleable] product from this shopping cart totally
     *
     * @param sellable the product to be removed
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
    @Throws(ProductNotFoundException::class)
    fun remove(sellable: Saleable) {
        if (!cartItemMap.containsKey(sellable)) throw ProductNotFoundException()
        val quantity = cartItemMap[sellable]!!
        cartItemMap.remove(sellable)
        totalPrice =
            totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity.toLong())))
        totalQuantity -= quantity
    }

    /**
     * Remove all products from this shopping cart
     */
    fun clear() {
        cartItemMap.clear()
        totalPrice = BigDecimal.ZERO
        totalQuantity = 0
    }

    /**
     * Get quantity of a [Saleable] product in this shopping cart
     *
     * @param sellable the product of interest which this method will return the quantity
     * @return The product quantity in this shopping cart
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
    @Throws(ProductNotFoundException::class)
    fun getQuantity(sellable: Saleable): Int {
        if (!cartItemMap.containsKey(sellable)) throw ProductNotFoundException()
        return cartItemMap[sellable]!!
    }

    /**
     * Get total cost of a [Saleable] product in this shopping cart
     *
     * @param sellable the product of interest which this method will return the total cost
     * @return Total cost of the product
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
//    @Throws(ProductNotFoundException::class)
//    fun getCost(sellable: Product?): BigDecimal? {
//        if (!cartItemMap.containsKey(sellable)) throw ProductNotFoundException()
//        return sellable?.price?.(cartItemMap[sellable]?.let { (it.toDouble()) })
//    }

    /**
     * Get set of products in this shopping cart
     *
     * @return Set of [Saleable] products in this shopping cart
     */
    val products: Set<Any>
        get() = cartItemMap.keys

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    val itemWithQuantity: MutableMap<Saleable, Int>
        get() {
            val cartItemMap: MutableMap<Saleable, Int> = HashMap<Saleable, Int>()
            cartItemMap.putAll(this.cartItemMap)
            return cartItemMap
        }

    override fun toString(): String {
        val strBuilder = StringBuilder()
        for ((key, value) in cartItemMap) {
            strBuilder.append(
                java.lang.String.format(
                    "Product: %s, Unit Price: %f, Quantity: %d%n", key.getName(), key.getPrice(),
                    value
                )
            )
        }
        strBuilder.append(
            String.format(
                "Total Quantity: %d, Total Price: %f",
                totalQuantity,
                totalPrice
            )
        )
        return strBuilder.toString()
    }

    companion object {
        private const val serialVersionUID = 42L
    }
}
