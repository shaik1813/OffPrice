package com.apparel.offprice.features.plp.presentation.screens

import com.apparel.offprice.R

data class ProductCardItems(
    val id: String,
    val tag: String?,           // e.g., "GOLD LABEL", "BEST PRICE"
    val image: List<Int>,
    val brand: String,
    val title: String,
    val sizes: List<String>,
    val basePrice: String,
    val discountPrice: String,
    val rrp: String,
    val discount: String,
    val delivery: String,       // e.g., "GET IT IN 90M" or "DELIVERY BY 06 NOV"
    val isWishlist: Boolean = false
)


val sampleProducts = listOf(
    ProductCardItems(
        id = "1",
        tag = "GOLD LABEL",
        image = listOf(
            R.drawable.product_item_1,
            R.drawable.product_item_2,
            R.drawable.product_item_3,
            R.drawable.product_item_4,
            R.drawable.colorimg,
            R.drawable.colorimg2
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "120.00",
        rrp = "172.00",
        discount = "90%",
        delivery = "GET IT IN 90MINS"
    ),
    ProductCardItems(
        id = "2",
        tag = "BEST PRICE",
        image = listOf(
            R.drawable.product_item_2,
            R.drawable.product_item_4
        ), // Replace with real image
        brand = "PUMA",
        title = "Men's Running Shoes",
        sizes = listOf("43", "44", "45"),
        basePrice = "161.00",
        discountPrice = "187.00",
        rrp = "220.00",
        discount = "73%",
        delivery = "DELIVERY BY 06 NOV, THU"
    ),
    ProductCardItems(
        id = "3",
        tag = "BESTSELLER",
        image = listOf(
            R.drawable.product_item_1,
            R.drawable.product_item_2,
            R.drawable.product_item_3,
            R.drawable.product_item_4
        ),
        brand = "NIKE",
        title = "Women's Stylish Top",
        sizes = listOf("S", "M", "L"),
        basePrice = "72.00",
        discountPrice = "",
        rrp = "120.00",
        discount = "40%",
        delivery = "DELIVERY BY TOMORROW"
    ),
    ProductCardItems(
        id = "4",
        tag = "GOLD LABEL",
        image = listOf(
            R.drawable.product_item_1,
        ),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT IN 90MINS"
    ),
    ProductCardItems(
        id = "5",
        tag = "GOLD LABEL",
        image = listOf(),  // Replace with real image
        brand = "ADIDAS",
        title = "Printed Shirt with Crew Neck Member with Polish Style",
        sizes = listOf("S", "M", "L", "XL"),
        basePrice = "35.00",
        discountPrice = "",
        rrp = "",
        discount = "",
        delivery = "GET IT IN 90MINS"
    ),
)
