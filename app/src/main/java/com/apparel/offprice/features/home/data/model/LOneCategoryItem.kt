package com.apparel.offprice.features.home.data.model

data class LOneCategoryItem(
    val id : Int = 0,
    val title: String = "",
    val image : String = "",
    val isSelected: Boolean = false
)

val sampleLOneCategoryItem = listOf(
    LOneCategoryItem(
        id = 1,
        title = "Men",
        image = "",
        isSelected = false
    ),
    LOneCategoryItem(
        id = 2,
        title = "Women",
        image = "",
        isSelected = false
    ),
    LOneCategoryItem(
        id = 3,
        title = "Kids",
        image = "",
        isSelected = false
    ),
    LOneCategoryItem(
        id = 4,
        title = "Sports",
        image = "",
        isSelected = false
    ),
    LOneCategoryItem(
        id = 5,
        title = "GoldLabel",
        image = "",
        isSelected = false
    )
)