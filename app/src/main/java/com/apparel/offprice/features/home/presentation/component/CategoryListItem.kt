package com.apparel.offprice.features.home.presentation.component

import com.apparel.offprice.R

data class CategoryListItem(
    val id: String,
    val title: String,
    val icon: Int
)

val sampleCategoryList = listOf(
    CategoryListItem("1", "NEW ARRIVALS", R.drawable.best_price_outline_icon),
    CategoryListItem("2", "CLOTHING", R.drawable.best_price_outline_icon),
    CategoryListItem("3", "SHOES", R.drawable.best_price_outline_icon),
    CategoryListItem("4", "ACCESSORIES", R.drawable.best_price_outline_icon),
    CategoryListItem("5", "SPORTS", R.drawable.best_price_outline_icon),
    CategoryListItem("6", "ABAYAS", R.drawable.best_price_outline_icon),
    CategoryListItem("7", "BRANDS", R.drawable.best_price_outline_icon),
    CategoryListItem("8", "TRAVEL", R.drawable.best_price_outline_icon),
    CategoryListItem("9", "LOYALTY PROGRAM", R.drawable.best_price_outline_icon)
)


//Banner
data class CategoryBannerItem(
    val id: String,
    val image: Int,
    val title: String? = null
)

val sampleCategoryBannerImages = listOf(
    R.drawable.best_price_outline_icon,
    R.drawable.bank_couponbg,
    R.drawable.best_price_outline_icon
)


//SubCategory
val sampleSubCategoryList = listOf(
    CategoryListItem("1", "T-SHIRT", R.drawable.best_price_outline_icon),
    CategoryListItem("2", "POLO T-SHIRT", R.drawable.best_price_outline_icon),
    CategoryListItem("3", "SHIRTS", R.drawable.best_price_outline_icon),
    CategoryListItem("4", "JEANS", R.drawable.best_price_outline_icon),
    CategoryListItem("5", "JOGGERS", R.drawable.best_price_outline_icon),
    CategoryListItem("6", "PANTS & CHINOS", R.drawable.best_price_outline_icon)
)
