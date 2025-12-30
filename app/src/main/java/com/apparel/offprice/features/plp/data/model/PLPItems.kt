package com.apparel.offprice.features.plp.data.model

import com.apparel.offprice.R

data class PLPHorizontalListItem(
    val id: String,
    val title: String,
    val img: Int
)

val samplePLPHorizontalListItems = listOf(
    LTwoCategoryListItem(1, "Clothings", R.drawable.category_item_1, true),
    LTwoCategoryListItem(2, "Shoes", R.drawable.category_item_2, false),
    LTwoCategoryListItem(3, "Sports", R.drawable.category_item_3, false),
    LTwoCategoryListItem(4, "Polos", R.drawable.category_item_4, false),
    LTwoCategoryListItem(5, "Innerwear", R.drawable.category_item_5, false),
    LTwoCategoryListItem(6, "Shorts", R.drawable.category_item_6, false)
)


data class LTwoCategoryListItem(
    val id: Int,
    val title: String,
    val image: Int,
    val isSelected : Boolean
)