package com.apparel.offprice.features.customerSupport.presentation.screens.disclaimer

import android.graphics.Typeface
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.resolveAsTypeface
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBarWithAction
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.customerSupport.presentation.component.HtmlText

@Composable
fun DisclaimerScreen(
    onNavigateToBack: () -> Unit,
    onSearchClicked: () -> Unit,
    onWishlistClicked: () -> Unit,
    viewModel: DisclaimerViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    // Collect any effects from the ViewModel
    effect.CollectInLaunchedEffect {
        when (it) {
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            DefaultTopAppBarWithAction(
                title = stringResource(R.string.label_disclaimer),
                onBackPressed = onNavigateToBack,
                onSearchClicked = onSearchClicked,
                onWishlistClicked = onWishlistClicked
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { paddingValues ->

        // Disclaimer content
        DisclaimerContent(
            modifier = Modifier.padding(paddingValues),
            disclaimerText = state.disclaimerText,
        )
    }
}

@Composable
fun DisclaimerContent(
    modifier: Modifier = Modifier,
    disclaimerText: String,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Check if content is HTML
        if (disclaimerText.contains("<") && disclaimerText.contains(">")) {
            HtmlText(html = disclaimerText)
        } else {
            PlainTextContent(text = disclaimerText)
        }


    }
}
@Composable
private fun PlainTextContent(
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = nonreturnTxtColor
        )
    }
}



