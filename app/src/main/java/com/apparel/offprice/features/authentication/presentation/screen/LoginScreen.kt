package com.apparel.offprice.features.authentication.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.routes.AppScreen


@Composable
fun LoginScreen(isVisible: Boolean, onItemClick: (AppScreen) -> Unit, onClose: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically { it } + fadeIn()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush =
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xF13B292A),
                                    Color(0XFF9F1D26)
                                ),
                            )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_bg), // your image
                    contentDescription = null,
                    modifier = Modifier
                        .width(326.dp)
                        .height(354.dp)
                        .align(Alignment.TopCenter)

                )

                Image(
                    painter = painterResource(R.drawable.close_login),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(22.dp)
                        .height(22.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            onClose()
                        })

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                        .align(Alignment.BottomCenter)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 26.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    LoginForm(onItemClick = { onItemClick(it) })
                }
            }
        }
    }

}
