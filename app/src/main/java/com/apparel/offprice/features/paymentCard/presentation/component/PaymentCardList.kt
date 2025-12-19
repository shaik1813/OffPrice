package com.apparel.offprice.features.paymentCard.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.features.paymentCard.data.PaymentCardModel

@Composable
fun PaymentCardList(
    paymentCards: List<PaymentCardModel>,
    onDeleteClicked: (PaymentCardModel) -> Unit,
    onDefaultChanged: (PaymentCardModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = paymentCards, key = { it.id }) { card ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFFB20B5B),
                                    Color(0xFF9E0F0F)
                                )
                            )
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Image(
                            painter = painterResource(R.drawable.card_bg_vector),
                            contentDescription = "image description",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.Start,

                        ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        // Top Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.icon_contact_less),
                                    contentDescription = null,
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = card.cardType,
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }

                            Image(
                                painter = painterResource(id = R.drawable.card_visa),
                                contentDescription = "image description",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(64.dp)
                                    .height(21.dp)
                            )
                        }

                        // Card Number
                        Text(
                            text = "* * * *    * * * *    * * * *    ${card.cardNumber.takeLast(4)}",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium.copy(
                                letterSpacing = 2.5.sp,
                                fontSize = 20.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                        )

                        // Expiry
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp, bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Expires",
                                color = Color.White.copy(alpha = 0.9f),
                                style = MaterialTheme.typography.bodySmall,

                                )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = card.expiryDate,
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall
                                    .copy(fontSize = 13.sp),

                                )
                        }

                        // Bottom Row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .clickable { onDeleteClicked(card) },
                                contentAlignment = Alignment.Center
                            ){
                                Icon(
                                    painter = painterResource(R.drawable.icon_delete_address),
                                    contentDescription = "Delete",
                                    tint = Color(0xFF93050C)
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Default Card",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Switch(
                                    checked = card.isDefault,
                                    onCheckedChange = { onDefaultChanged(card) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        uncheckedThumbColor = Color.White
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}