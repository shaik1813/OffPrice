package com.apparel.offprice.features.welcome.data.model




//Location Selection
data class LocationSelectionItem(
    val id: String,
    val title: String,
    val arabicTitle: String,
    val img: String
)

val sampleLocationSelectionItems = listOf(
    LocationSelectionItem("1", "UAE", "الإمارات", "https://plus.unsplash.com/premium_photo-1669324357471-e33e71e3f3d8?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8dXJsfGVufDB8fDB8fHww"),
    LocationSelectionItem("2", "QATAR", "دولة قطر", "https://media.istockphoto.com/id/2194166576/photo/three-accessibility-icon-on-computer-keyboard.webp?a=1&b=1&s=612x612&w=0&k=20&c=HwJp5u5WJ49JKrIz2de_E--J5Vidi4HRKFfWgfzwa-U="),
    LocationSelectionItem("3", "KSA", "السعودية", "https://media.istockphoto.com/id/2231090399/photo/wifi-over-modern-american-houses-internet-connected-broadband-in-suburban-town-graphic.webp?a=1&b=1&s=612x612&w=0&k=20&c=GuzBrIeOTxYknGMxVgODdbvlUPAFFhUN6UeXTkKGamA="),
    LocationSelectionItem("4", "Oman", "سلطنة عمان", "https://media.istockphoto.com/id/2194166576/photo/three-accessibility-icon-on-computer-keyboard.webp?a=1&b=1&s=612x612&w=0&k=20&c=HwJp5u5WJ49JKrIz2de_E--J5Vidi4HRKFfWgfzwa-U="),
    LocationSelectionItem("5", "KUWAIT", "الكويت", "https://media.istockphoto.com/id/2231090399/photo/wifi-over-modern-american-houses-internet-connected-broadband-in-suburban-town-graphic.webp?a=1&b=1&s=612x612&w=0&k=20&c=GuzBrIeOTxYknGMxVgODdbvlUPAFFhUN6UeXTkKGamA="),
    LocationSelectionItem("6", "BAHRAIN", "البحرين", "https://media.istockphoto.com/id/2194166576/photo/three-accessibility-icon-on-computer-keyboard.webp?a=1&b=1&s=612x612&w=0&k=20&c=HwJp5u5WJ49JKrIz2de_E--J5Vidi4HRKFfWgfzwa-U=")
)


//Gender Category Selection
data class GenderCategoryItem(
    val id: String,
    val label: String,
    val img: String   // drawable image
)

val genderCategories = listOf(
    GenderCategoryItem("men", "MEN", "https://plus.unsplash.com/premium_photo-1669324357471-e33e71e3f3d8?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8dXJsfGVufDB8fDB8fHww"),
    GenderCategoryItem("women", "WOMEN", "https://media.istockphoto.com/id/2194166576/photo/three-accessibility-icon-on-computer-keyboard.webp?a=1&b=1&s=612x612&w=0&k=20&c=HwJp5u5WJ49JKrIz2de_E--J5Vidi4HRKFfWgfzwa-U="),
    GenderCategoryItem("kids", "KIDS", "https://media.istockphoto.com/id/2231090399/photo/wifi-over-modern-american-houses-internet-connected-broadband-in-suburban-town-graphic.webp?a=1&b=1&s=612x612&w=0&k=20&c=GuzBrIeOTxYknGMxVgODdbvlUPAFFhUN6UeXTkKGamA=")
)