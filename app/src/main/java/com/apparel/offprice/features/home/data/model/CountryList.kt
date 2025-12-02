package com.apparel.offprice.features.home.data.model

import com.apparel.offprice.R


data class Country(
    val countryName: String,
    val countryCode: String,
    val countryFlagRound: Int = R.drawable.icon_flag_uae_round,
    val countryFlag: Int = R.drawable.icon_flag_uae,
    val countryNameArabic: String
)


val countryList = listOf(
    Country(
        countryName = "UAE",
        countryCode = "+971",
        countryFlagRound = R.drawable.icon_flag_uae_round,
        countryFlag = R.drawable.icon_flag_uae,
        countryNameArabic = "الإمارات"
    ),
    Country(
        countryName = "QATAR",
        countryCode = "+974",
        countryFlagRound = R.drawable.icon_flag_qatar_round,
        countryFlag = R.drawable.icon_flag_qatar,
        countryNameArabic = "دولة قطر"
    ),
    Country(
        countryName = "KSA",
        countryCode = "+966",
        countryFlagRound = R.drawable.icon_flag_saudi_round,
        countryFlag = R.drawable.icon_flag_saudi,
        countryNameArabic = "السعودية"
    ),
    Country(
        countryName = "OMAN",
        countryCode = "+968",
        countryFlagRound = R.drawable.icon_flag_oman_round,
        countryFlag = R.drawable.icon_flag_oman,
        countryNameArabic = "سلطنة عمان"
    ),
    Country(
        countryName = "KUWAIT",
        countryCode = "+965",
        countryFlagRound = R.drawable.icon_flag_kuwait_round,
        countryFlag = R.drawable.icon_flag_kuwait,
        countryNameArabic = "الكويت"
    ),
    Country(
        countryName = "BAHRAIN",
        countryCode = "+973",
        countryFlagRound = R.drawable.icon_flag_bahrain_round,
        countryFlag = R.drawable.icon_flag_bahrain,
        countryNameArabic = "البحرين"
    )
)