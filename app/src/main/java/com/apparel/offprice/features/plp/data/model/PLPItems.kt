package com.apparel.offprice.features.plp.data.model

import com.apparel.offprice.R

data class PLPHorizontalListItem(
    val id: String,
    val title: String,
    val img: Int
)

val samplePLPHorizontalListItems = listOf(
    PLPHorizontalListItem("1", "Clothings", R.drawable.best_price_fill_icon),
    PLPHorizontalListItem("2", "Shoes", R.drawable.best_price_fill_icon),
    PLPHorizontalListItem("3", "Sports", R.drawable.best_price_fill_icon),
    PLPHorizontalListItem("4", "Polos", R.drawable.best_price_fill_icon),
    PLPHorizontalListItem("5", "Innerwear", R.drawable.best_price_fill_icon)
)