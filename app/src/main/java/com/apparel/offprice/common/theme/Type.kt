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
    // Display Font Styles For WixMadeForDisplay
    titleLarge = TextStyle(
        // Main Title
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    ),
    titleMedium = TextStyle(
        //Top AppBar,
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    titleSmall = TextStyle(
        // Sub Title
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    // Display Font Styles For WixMadeForDisplay
    bodyLarge = TextStyle(
        // Product Title, Price
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    bodyMedium = TextStyle(
        // Product Description
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    bodySmall = TextStyle(
        // Product Size
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
    ),
    // Display Font Styles For WixMadeForDisplay
    labelLarge = TextStyle(
        // Product Title, Price in PDP
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        // Product Description in PDP
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    labelSmall = TextStyle(
        //small font for size
        fontFamily = wixMadeForDisplayFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    // Display Font Styles For WixMadeForText
    displaySmall = TextStyle(
        // Delivery Time
        fontFamily = wixMadeForTextFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        fontStyle = FontStyle.Italic,
    ),
    displayMedium = TextStyle(
        // Product Label
        fontFamily = wixMadeForTextFamily,
        fontWeight = FontWeight.W600,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontStyle = FontStyle.Italic,
    ),
    displayLarge = TextStyle(
        fontFamily = wixMadeForTextFamily,
        fontWeight = FontWeight.W700,
        fontSize = 10.sp,
        fontStyle = FontStyle.Italic,
    )
)