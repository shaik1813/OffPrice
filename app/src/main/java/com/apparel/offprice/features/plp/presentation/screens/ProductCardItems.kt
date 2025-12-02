package com.apparel.offprice.features.plp.presentation.screens

import com.apparel.offprice.R

data class ProductCardItems(
    val id: String,
    val tag: String?,           // e.g., "GOLD LABEL", "BEST PRICE"
    val image: Int,
    val brand: String,
    val title: String,
    val sizes: List<String>,
    val price: String,
    val rrp: String,
    val discount: String,
    val delivery: String,       // e.g., "GET IT IN 90M" or "DELIVERY BY 06 NOV"
    val isWishlist: Boolean = false
)


val sampleProducts = listOf(
    ProductCardItems(
        id = "1",
        tag = "GOLD LABEL",
        image = R.drawable.icon_payment_card,  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck",
        sizes = listOf("S", "M", "L", "XL"),
        price = "35.00",
        rrp = "172.00",
        discount = "90%",
        delivery = "GET IT IN 90M"
    ),
    ProductCardItems(
        id = "2",
        tag = "BEST PRICE",
        image = R.drawable.icon_payment_card, // Replace with real image
        brand = "PUMA",
        title = "Men's Running Shoes",
        sizes = listOf("43", "44", "45"),
        price = "161.00",
        rrp = "220.00",
        discount = "73%",
        delivery = "DELIVERY BY 06 NOV, THU"
    ),
    ProductCardItems(
        id = "3",
        tag = "BESTSELLER",
        image = R.drawable.icon_payment_card,
        brand = "NIKE",
        title = "Women's Stylish Top",
        sizes = listOf("S", "M", "L"),
        price = "72.00",
        rrp = "120.00",
        discount = "40%",
        delivery = "DELIVERY BY TOMORROW"
    )
)
