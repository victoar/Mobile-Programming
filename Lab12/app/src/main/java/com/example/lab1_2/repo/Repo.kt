package com.example.lab1_2.repo

import com.example.lab1_2.model.Product

object Repo {
    var productList = ArrayList<Product>()
    var id: Int = 0

    init {
        var product : Product = Product(getNextAvailableId(), "Flour", 100, 100, "g", "10.12.2022", 20)
        addProduct(product)
        var product1 : Product = Product(getNextAvailableId(), "Flour", 100, 100, "g","10.11.2022", 20)
        addProduct(product1)
    }

    fun addProduct(product: Product) {
        productList.add(product)
        id++
    }

    fun update(id: Int, productQuantity: String, productDesiredQuantity: String, productExpirationDate: String,
        productPrice: String) {
        val pos = productList.indexOf(findById(id))
        productList[pos].quantity = productQuantity.toInt()
        productList[pos].desiredQuantity = productDesiredQuantity.toInt()
        productList[pos].date = productExpirationDate
        productList[pos].price = productPrice.toInt()
    }

    fun delete(id: Int) {
        productList.remove(findById(id))
    }

    fun findById(id: Int): Product? {
        return productList.find {product -> product.productId == id}
    }

    fun getNextAvailableId(): Int {
        return id
    }
}