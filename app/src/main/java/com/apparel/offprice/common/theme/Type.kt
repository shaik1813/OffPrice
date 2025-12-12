package com.apparel.offprice.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R

@OptIn(ExperimentalTextApi::class)
val wixMadeForDisplayFamily = FontFamily(
    Font(resId = R.font.wixmadefordisplay_regular, weight = FontWeight.Normal),
    Font(resId = R.font.wixmadefordisplay_medium, weight = FontWeight.Medium),
    Font(
        resId = R.font.wixmadefordisplay_semibold,
        weight = FontWeight.SemiBold,
        variationSettings = FontVariation.Settings(FontVariation.weight(600))
    ),
    Font(resId = R.font.wixmadefordisplay_bold, weight = FontWeight.Bold),
)

val wixMadeForTextFamily = FontFamily(
    Font(resId = R.font.wixmadefortext_regular, weight = FontWeight.Normal),
    Font(resId = R.font.wixmadefortext_italic, weight = FontWeight.Normal),
    Font(resId = R.font.wixmadefortext_medium, weight = FontWeight.Medium),
    Font(resId = R.font.wixmadefortext_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.wixmadefortext_bold, weight = FontWeight.Bold)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 14.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = wixMadeForTextFamily,
        fontWeight = FontWeight.W700,
        fontSize = 10.sp,
        fontStyle = FontStyle.Italic,
    ),

    )