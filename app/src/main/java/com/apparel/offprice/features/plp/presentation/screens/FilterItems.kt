package com.apparel.offprice.features.plp.presentation.screens

import androidx.compose.ui.graphics.Color

data class FilterItem(
    val id: String,
    val label: String
)

data class CategoryFilterItem(
    val id: String,
    val title: String,
    val count: Int,
    var selected: Boolean = false
)

data class ColorFilterItem(
    val id: String,
    val color: Color,
    val name: String,
    val count: Int,
    var selected: Boolean = false
)




val filterMenuItems = listOf(
    FilterItem("brand", "Brand"),
    FilterItem("categories", "Categories"),
    FilterItem("size", "Size"),
    FilterItem("color", "Color"),
    FilterItem("price", "Price"),
    FilterItem("discount", "Discount"),
    FilterItem("delivery", "Delivery Type"),
    FilterItem("occasion", "Occasion"),
    FilterItem("stock", "By stock")
)

val sampleCategoryList = listOf(
    CategoryFilterItem("1", "T-Shirts", 10),
    CategoryFilterItem("2", "Shirts", 13),
    CategoryFilterItem("3", "Polo Shirts", 6),
    CategoryFilterItem("4", "Pants", 23),
    CategoryFilterItem("5", "Hoodies & Sweats", 2),
    CategoryFilterItem("6", "Underwear & Socks", 15),
)

val sampleColorList = listOf(
    ColorFilterItem("1", Color.Black, "Black", 15),
    ColorFilterItem("2", Color.White, "White", 10),
    ColorFilterItem("3", Color(0xFF0288D1), "Blue", 16),
    ColorFilterItem("4", Color(0xFF4CAF50), "Green", 6),
    ColorFilterItem("5", Color(0xFFFF9800), "Orange", 10),
    ColorFilterItem("6", Color(0xFF9C27B0), "Violet", 3)
)
