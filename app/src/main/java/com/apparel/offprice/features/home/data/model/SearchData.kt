package com.apparel.offprice.features.home.data.model

val trendingSearch = listOf(
    "Mens T shirt",
    "Cargo Pants",
    "Luggage",
    "American Eagle Shirt",
    "Men's Sunglasses",
    "Linen Pants",
    "Linen Shirt",
    "Watches For Men",
    "Oversized T Shirts",
    "N Shoes 42"
)


val recentSearch = listOf("Shoes", "Shirt", "Hoodies")

enum class SearchCategory(val title: String) {
    All("ALL"), Men("MEN"), Women("WOMEN"), Kids("KIDS")
}

