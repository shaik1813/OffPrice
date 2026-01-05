package com.apparel.offprice.features.home.presentation.component

import com.apparel.offprice.R

data class CategoryListItem(
    val id: String,
    val title: String,
    val icon: Int
)

val sampleCategoryList = listOf(
    CategoryListItem("1", "NEW ARRIVALS", R.drawable.category_one_image),
    CategoryListItem("2", "CLOTHING", R.drawable.category_two_image),
    CategoryListItem("3", "SHOES", R.drawable.category_three_image),
    CategoryListItem("4", "ACCESSORIES", R.drawable.category_four_image),
    CategoryListItem("5", "SPORTS", R.drawable.category_one_image),
    CategoryListItem("6", "ABAYAS", R.drawable.category_two_image),
    CategoryListItem("7", "BRANDS", R.drawable.category_three_image),
    CategoryListItem("8", "TRAVEL", R.drawable.category_four_image),
    CategoryListItem("9", "LOYALTY PROGRAM", R.drawable.category_one_image)
)


//Banner
data class CategoryBannerItem(
    val id: String,
    val image: Int,
    val title: String? = null
)

val sampleCategoryBannerImages = listOf(
    CategoryBannerItem("1", R.drawable.category_flash_sale, "Item1"),
    CategoryBannerItem("2", R.drawable.category_flash_sale, "Item2"),
    CategoryBannerItem("3", R.drawable.category_flash_sale, "Item3")
)


//SubCategory
val sampleSubCategoryList = listOf(
    CategoryListItem("1", "T-SHIRT", R.drawable.category_one_image),
    CategoryListItem("2", "POLO T-SHIRT", R.drawable.category_two_image),
    CategoryListItem("3", "SHIRTS", R.drawable.category_three_image),
    CategoryListItem("4", "JEANS", R.drawable.category_four_image),
    CategoryListItem("5", "JOGGERS", R.drawable.category_one_image),
    CategoryListItem("6", "PANTS & CHINOS", R.drawable.category_one_image)
)
