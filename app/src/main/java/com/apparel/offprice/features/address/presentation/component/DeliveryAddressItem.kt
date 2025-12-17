package com.apparel.offprice.features.address.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.features.address.data.model.DeliveryAddressModel

@Composable
fun DeliveryAddressItemList(
    deliveryAddress: List<DeliveryAddressModel>,
    onEditClicked: (DeliveryAddressModel) -> Unit,
    onDeleteClicked: (DeliveryAddressModel) -> Unit
) {
    LazyColumn {
        items(items = deliveryAddress, key = { it.id }) { address ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (address.isDefault) {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50)),
                                shape = RoundedCornerShape(4.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.label_default),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(vertical = 4.dp, horizontal = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                            shape = RoundedCornerShape(4.dp),
                        ) {
                            Text(
                                text = address.tag.name,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = address.userName,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_location_black),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = address.address,
                            style = MaterialTheme.typography.bodySmall,
                            color = nonreturnTxtColor,
                            maxLines = 3
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.icon_phone),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = address.phone,
                                style = MaterialTheme.typography.bodySmall,
                                color = nonreturnTxtColor
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painterResource(R.drawable.icon_edit_address),
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable {
                                        onEditClicked(address)
                                    }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Image(
                                painterResource(R.drawable.icon_delete_address),
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable {
                                        onDeleteClicked(address)
                                    }
                            )
                        }
                    }
                }

            }
        }
    }
}