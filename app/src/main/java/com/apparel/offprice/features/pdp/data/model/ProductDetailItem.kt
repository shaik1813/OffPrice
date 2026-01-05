package com.apparel.offprice.features.pdp.data.model

data class ProductDetailItem(
    val id: String,
    val tag: String?,           // e.g., "GOLD LABEL", "BEST PRICE"
    val tagContainerColor: String,
    val tagContentColor: String,
    val image: List<Int>,
    val brand: String,
    val colorImages: List<Int>,
    val title: String,
    val sizes: List<String>,
    val basePrice: String,
    val discountPrice: String,
    val rrp: String,
    val discount: String,
    val delivery: String,       // e.g., "GET IT IN 90M" or "DELIVERY BY 06 NOV"
    val isWishlist: Boolean = false
)
