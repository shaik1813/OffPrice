package com.apparel.offprice.features.checkout.presentation.components

import com.apparel.offprice.R

data class ProductItem(
    val id: String,
    val brand: String,
    val title: String,
    val image: Int,
    val color: String,
    val size: String,
    val qty: Int,
    val price: String,
    val mrp: String,
    val discountText: String,
    val deliveryText: String
)


val sampleProductsOSS = listOf(
    ProductItem(
        id = "1",
        brand = "ZARA",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        image = R.drawable.product_item_1,
        color = "Blue",
        size = "L",
        qty = 1,
        price = "35.00",
        mrp = "172.00",
        discountText = "90% OFF",
        deliveryText = "DELIVERY BY 06 NOV, THU"
    ),
    ProductItem(
        id = "2",
        brand = "NIKE",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        image = R.drawable.product_item_2,
        color = "Blue",
        size = "L",
        qty = 1,
        price = "35.00",
        mrp = "172.00",
        discountText = "90% OFF",
        deliveryText = "GET IT TODAY"
    ),
    ProductItem(
        id = "1",
        brand = "MAXX",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        image = R.drawable.product_item_3,
        color = "Blue",
        size = "L",
        qty = 1,
        price = "35.00",
        mrp = "172.00",
        discountText = "90% OFF",
        deliveryText = "DELIVERY BY 06 NOV, THU"
    )
)


enum class ShippingAddressFilter {
    DELIVERY, PICKUPATSTORE
}