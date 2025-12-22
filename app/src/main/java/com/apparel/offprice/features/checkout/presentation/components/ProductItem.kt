package com.apparel.offprice.features.checkout.presentation.components

import com.apparel.offprice.R

data class ProductItem(
    val id: String,
    val brand: String,
    val title: String,
    val price: Double,
    val oldPrice: Double? = null,
    val color: String,
    val qty: Int,
    val size: String,
    val image: Int
)


val sampleProductsOSS = listOf(
    ProductItem(
        id = "1",
        brand = "ADIDAS",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        price = 35.00,
        oldPrice = 172.00,
        color = "Blue",
        qty = 1,
        size = "L",
        image = R.drawable.best_price_fill_icon // Replace with real image
    ),
    ProductItem(
        id = "2",
        brand = "NIKE",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        price = 35.00,
        oldPrice = 172.00,
        color = "Black",
        qty = 1,
        size = "L",
        image = R.drawable.best_price_fill_icon
    ),
    ProductItem(
        id = "3",
        brand = "PUMA",
        title = "Printed Shirt With Crew Neck And Short Sleeves",
        price = 35.00,
        oldPrice = 172.00,
        color = "Gray",
        qty = 1,
        size = "L",
        image = R.drawable.best_price_fill_icon
    )
)
