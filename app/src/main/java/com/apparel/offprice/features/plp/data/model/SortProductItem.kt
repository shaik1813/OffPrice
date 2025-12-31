package com.apparel.offprice.features.plp.data.model

data class SortProductItem(
    val id: Int,
    val title: String,
    val isSelected : Boolean
)


val sampleSortList = listOf(
    SortProductItem(1, "Recommended", true),
    SortProductItem(2, "Price: High to Low", false),
    SortProductItem(3, "Price: Low to High", false),
    SortProductItem(4, "New Arrivals", false),
    SortProductItem(5 ,"Best Rated", false),
)
