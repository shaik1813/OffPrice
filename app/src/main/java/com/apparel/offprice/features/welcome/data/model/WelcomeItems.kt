package com.apparel.offprice.features.welcome.data.model

import com.apparel.offprice.R


//Gender Category Selection
data class GenderCategoryItem(
    val id: Int,
    val label: String,
    val image: Int
)

val genderCategories = listOf(
    GenderCategoryItem(
        id = 1,
        label = "MEN",
        image = R.drawable.image_men
    ),
    GenderCategoryItem(
        id = 2,
        label = "WOMEN",
        image = R.drawable.image_women,
    ),
    GenderCategoryItem(
        id = 3,
        label = "KIDS",
        image = R.drawable.image_kid
    )
)