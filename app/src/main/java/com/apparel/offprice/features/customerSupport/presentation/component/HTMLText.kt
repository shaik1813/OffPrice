package com.apparel.offprice.features.customerSupport.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.apparel.offprice.common.theme.nonreturnTxtColor

@Composable
fun HtmlText(html: String) {
    val spanned = remember(html) {
        HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    Text(
        text = buildAnnotatedString {
            append(spanned.toString())
        },
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.labelSmall,
        lineHeight = 18.sp,
        color = nonreturnTxtColor,
        textAlign = TextAlign.Center
    )
}
