package com.apparel.offprice.features.welcome.data.model

import androidx.compose.ui.graphics.Color
import com.apparel.offprice.R


//Gender Category Selection
data class GenderCategoryItem(
    val id: String,
    val label: String,
    val image: Int,
    val backgroundColor: Pair<Color, Color> = Pair(first = Color(0xFFE6E6E6), second = Color(0xFFE6E6E6)),
)

val genderCategories = listOf(
    GenderCategoryItem(
        id = "men",
        label = "MEN",
        image = R.drawable.image_men
    ),
    GenderCategoryItem(
        id = "women",
        label = "WOMEN",
        image = R.drawable.image_women,
        backgroundColor = Pair(first = Color(0XFF4487AC), second = Color(0xFFA5CCE2))
    ),
    GenderCategoryItem(
        id = "kids",
        label = "KIDS",
        image = R.drawable.image_kid
    )
)