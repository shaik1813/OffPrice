package com.apparel.offprice.features.welcome.data.model

import android.R


//Location Selection
data class LocationSelectionItem(
    val id: String,
    val title: String,
    val arabicTitle: String,
    val img: Int
)

val sampleLocationSelectionItems = listOf(
    LocationSelectionItem("1", "UAE", "الإمارات", R.drawable.ic_menu_gallery),
    LocationSelectionItem("2", "QATAR", "دولة قطر", R.drawable.ic_menu_gallery),
    LocationSelectionItem("3", "KSA", "السعودية", R.drawable.ic_menu_gallery),
    LocationSelectionItem("4", "Oman", "سلطنة عمان", R.drawable.ic_menu_gallery),
    LocationSelectionItem("5", "KUWAIT", "الكويت", R.drawable.ic_menu_gallery),
    LocationSelectionItem("6", "BAHRAIN", "البحرين", R.drawable.ic_menu_gallery)
)


//Gender Category Selection
data class GenderCategoryItem(
    val id: String,
    val label: String,
    val img: Int   // drawable image
)

val genderCategories = listOf(
    GenderCategoryItem("men", "MEN", R.drawable.ic_menu_gallery),
    GenderCategoryItem("women", "WOMEN", R.drawable.ic_menu_gallery),
    GenderCategoryItem("kids", "KIDS", R.drawable.ic_menu_gallery)
)