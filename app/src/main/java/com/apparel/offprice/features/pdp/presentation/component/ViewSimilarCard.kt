package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R

@Composable
fun ViewSimilarCard(modifier: Modifier,onSimilarClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
        ) {
        Box() {
            Row(modifier = Modifier.align(Alignment.Center).padding(vertical = 6.dp, horizontal = 12.dp)) {

                Image(
                    painter = painterResource(R.drawable.similar_icon),
                    contentDescription = null, Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = stringResource(com.apparel.offprice.R.string.view_similar),
                    color = Color.Black, style = MaterialTheme.typography.labelMedium
                )

            }
        }
    }
}