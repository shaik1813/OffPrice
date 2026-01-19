package com.apparel.offprice.features.customerSupport.presentation.component

import android.graphics.Typeface
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.resolveAsTypeface
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.nonreturnTxtColor

@Composable
fun HtmlContent(
    htmlText: String,
    modifier: Modifier = Modifier
) {
    val bodyMedium = MaterialTheme.typography.bodyMedium
    val textColor = nonreturnTxtColor

    val resolver = LocalFontFamilyResolver.current
    val typeface: Typeface = resolver.resolveAsTypeface(
        fontFamily = FontFamily(Font(resId = R.font.wixmadefordisplay_medium, weight = FontWeight.Medium)),
        fontWeight = bodyMedium.fontWeight ?: FontWeight.Normal,
        fontStyle = bodyMedium.fontStyle ?: FontStyle.Normal
    ).value

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            TextView(context).apply {
                // Allows clicking on links (like emails)
                movementMethod = LinkMovementMethod.getInstance()

                // Apply the exact styling
                setTextColor(textColor.toArgb())
                setTypeface(typeface)

                // Set text size in SP (important for accessibility)
                textSize = bodyMedium.fontSize.value

                // Set line spacing to match Compose line height (roughly)
                //setLineSpacing(0f, 1.2f)
            }
        },
        update = { textView ->
            textView.text = HtmlCompat.fromHtml(
                htmlText,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        }
    )
}