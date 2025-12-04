package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.carousel.ImageSliderWithIndicatorPDP


@Composable
fun ProductImageSection() {
    val images = listOf(
        "https://plus.unsplash.com/premium_photo-1669324357471-e33e71e3f3d8?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8dXJsfGVufDB8fDB8fHww",
        "https://plus.unsplash.com/premium_photo-1690303193898-f9c721d0770b?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fHVybHxlbnwwfHwwfHx8MA%3D%3D",
        "https://media.istockphoto.com/id/2231090399/photo/wifi-over-modern-american-houses-internet-connected-broadband-in-suburban-town-graphic.webp?a=1&b=1&s=612x612&w=0&k=20&c=GuzBrIeOTxYknGMxVgODdbvlUPAFFhUN6UeXTkKGamA=",
        "https://media.istockphoto.com/id/2194166576/photo/three-accessibility-icon-on-computer-keyboard.webp?a=1&b=1&s=612x612&w=0&k=20&c=HwJp5u5WJ49JKrIz2de_E--J5Vidi4HRKFfWgfzwa-U="
    )

    Box() {
        ImageSliderWithIndicatorPDP(images)

        Image(
            painter = painterResource(id = R.drawable.back_icon),
            contentDescription = "App Icon",
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        )
        Column(modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.share_icon),
                contentDescription = "App Icon",
                modifier = Modifier.size(40.dp).align(Alignment.End)
            )

            Spacer(modifier = Modifier.size(12.dp))

            Image(
                painter = painterResource(id = R.drawable.heart_pdpicon),
                contentDescription = "App Icon",
                modifier = Modifier.size(40.dp).align(Alignment.End)
            )

            Spacer(modifier = Modifier.size(12.dp))

            Image(
                painter = painterResource(id = R.drawable.cart_icon),
                contentDescription = "share Icon",
                modifier = Modifier.size(40.dp).align(Alignment.End)
            )
        }

    }

}