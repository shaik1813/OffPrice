package com.apparel.offprice.features.home.data.model



data class TopTabItem(
    val id: String,
    val label: String
)

var sampleTopTabs = listOf(
    TopTabItem("1", "Men"),
    TopTabItem("2", "Women"),
    TopTabItem("3", "Kids")
)


//Categories List
data class CategoryItem(
    val id: String,
    val title: String,
    val img: String
)



// SAMPLE CATEGORY ITEMS
val sampleCategoryItems = listOf(
    CategoryItem("cat_1", "Category 1", "https://plus.unsplash.com/premium_photo-1669324357471-e33e71e3f3d8?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8dXJsfGVufDB8fDB8fHww"),
    CategoryItem("cat_2", "Category 2", "https://media.istockphoto.com/id/2194166576/photo/three-accessibility-icon-on-computer-keyboard.webp?a=1&b=1&s=612x612&w=0&k=20&c=HwJp5u5WJ49JKrIz2de_E--J5Vidi4HRKFfWgfzwa-U="),
    CategoryItem("cat_3", "Category 3", "https://media.istockphoto.com/id/2231090399/photo/wifi-over-modern-american-houses-internet-connected-broadband-in-suburban-town-graphic.webp?a=1&b=1&s=612x612&w=0&k=20&c=GuzBrIeOTxYknGMxVgODdbvlUPAFFhUN6UeXTkKGamA="),
    CategoryItem("cat_4", "Category 4", "https://plus.unsplash.com/premium_photo-1669324357471-e33e71e3f3d8?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8dXJsfGVufDB8fDB8fHww"),
    CategoryItem("cat_5", "Category 5", "https://media.istockphoto.com/id/2194166576/photo/three-accessibility-icon-on-computer-keyboard.webp?a=1&b=1&s=612x612&w=0&k=20&c=HwJp5u5WJ49JKrIz2de_E--J5Vidi4HRKFfWgfzwa-U="),
    CategoryItem("cat_6", "Category 6", "https://media.istockphoto.com/id/2231090399/photo/wifi-over-modern-american-houses-internet-connected-broadband-in-suburban-town-graphic.webp?a=1&b=1&s=612x612&w=0&k=20&c=GuzBrIeOTxYknGMxVgODdbvlUPAFFhUN6UeXTkKGamA="),

)




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