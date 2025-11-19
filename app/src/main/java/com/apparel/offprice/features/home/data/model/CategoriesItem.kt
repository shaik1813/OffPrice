package com.apparel.offprice.features.home.data.model


data class CategoryItem(
    val id: String,
    val title: String,
    val img: Int
)


data class DrawerTab(
    val id: String,
    val label: String
)


// SAMPLE CATEGORY ITEMS
val sampleCategoryItems = List(10) {
    CategoryItem(
        id = "cat_$it",
        title = "Category $it",
        img = android.R.drawable.ic_menu_gallery
    )
}

// SAMPLE DYNAMIC TABS
val sampleTopTabs = listOf(
    DrawerTab("tab_men", "Men"),
    DrawerTab("tab_women", "Women"),
    DrawerTab("tab_kids", "Kids")
)