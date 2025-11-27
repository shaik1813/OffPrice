package com.apparel.offprice.features.home.data.model

import com.apparel.offprice.R


data class Country(
    val countryName: String,
    val countryCode: String,
    val countryFlag : Int = R.drawable.icon_flag_uae
)
//val countries : List<Pair<String,Int>> = listOf(
//    "UAE" to R.drawable.icon_flag_uae,
//    "QATAR" to R.drawable.icon_flag_uae,
//    "KSA" to R.drawable.icon_flag_uae,
//    "OMAN" to R.drawable.icon_flag_uae,
//    "KUWAIT" to R.drawable.icon_flag_uae,
//    "BAHRAIN" to R.drawable.icon_flag_uae
//)

val countryList = listOf(
    Country(countryName = "UAE", countryCode = "+971" , countryFlag = R.drawable.icon_flag_uae),
    Country(countryName = "QATAR", countryCode = "+974" , countryFlag = R.drawable.icon_flag_qatar),
    Country(countryName = "KSA", countryCode = "+966" , countryFlag = R.drawable.icon_flag_saudi),
    Country(countryName = "OMAN", countryCode = "+968" , countryFlag = R.drawable.icon_flag_oman),
    Country(countryName = "KUWAIT", countryCode = "+965" , countryFlag = R.drawable.icon_flag_kuwait),
    Country(countryName = "BAHRAIN", countryCode = "+973" , countryFlag = R.drawable.icon_flag_bahrain)
)