package com.apparel.offprice.features.home.data.model



data class TopTabItem(
    val id: String,
    val label: String
)

var sampleTopTabs = listOf(
    TopTabItem("men", "Men"),
    TopTabItem("women", "Women"),
    TopTabItem("kids", "Kids")
)


//Categories List
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
/*val sampleTopTabs = listOf(
    DrawerTab("tab_men", "Men"),
    DrawerTab("tab_women", "Women"),
    DrawerTab("tab_kids", "Kids")
)*/



//SubCategories

enum class DrawerMode {
    CATEGORY_LIST,
    SUBCATEGORY_LIST
}

data class SubCategoryItem(
    val id: String,
    val title: String
)

val sampleSubCategories = listOf(
    SubCategoryItem("1", "T-Shirt"),
    SubCategoryItem("2", "Polo T-Shirt"),
    SubCategoryItem("3", "Shirts"),
    SubCategoryItem("4", "Jeans"),
    SubCategoryItem("5", "Joggers"),
    SubCategoryItem("6", "Pants & Chinos"),
    SubCategoryItem("7", "Shorts"),
    SubCategoryItem("8", "Trackpants")
)