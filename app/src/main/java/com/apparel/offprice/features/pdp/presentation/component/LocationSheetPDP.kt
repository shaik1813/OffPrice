package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.loginButtonColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSheetPDP(
    selectedCity: String,
    onCitySelected: (String) -> Unit,
    onDismiss: () -> Unit
) {


    val sheetState = rememberModalBottomSheetState()
    val cities = listOf(
        "Abu Dhabi", "Ajman", "Al Ain", "Dubai",
        "Fujairah", "Ras Al Khaimah", "Sharjah",
        "Umm Al Quwain", "Western Region"
    )

    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("City") }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(com.apparel.offprice.R.string.select_city),
                    style = MaterialTheme.typography.titleMedium,
                    color = loginButtonColor
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ---------- Toggle (City / Area) ----------
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, color = Color(0xFFACACAC), RoundedCornerShape(10.dp))
                        .height(60.dp)
                        .padding(8.dp)
                        .wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    listOf("City", "Area").forEach { text ->
                        val isSelected = text == selectedTab
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .fillMaxHeight()
                                .width(100.dp)
                                .clickable { selectedTab = text }
                                .background(if (isSelected) loginButtonColor else Color.White)
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = text,
                                color = if (isSelected) Color.White else loginButtonColor,
                                fontSize = 14.sp,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            /*Search part*/
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        color = Color(0xFFACACAC),
                        shape = RoundedCornerShape(4.dp)
                    )

            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            stringResource(com.apparel.offprice.R.string.search_city),
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color(0xFF8A8A8A),
                            style = MaterialTheme.typography.titleSmall
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),

                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.icon_search),
                            contentDescription = null
                        )
                    }
                )
            }


            Spacer(modifier = Modifier.height(6.dp))

            /*List data*/
            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.6f) // Controls sheet height
            ) {
                items(cities.filter { it.contains(searchQuery, true) }) { city ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onCitySelected(city) }

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = city,
                                fontSize = 14.sp,
                                style = MaterialTheme.typography.titleMedium,
                                color = inputTextColor,
                                modifier = Modifier.weight(1f)
                            )

                            if (city == selectedCity) {
                                Icon(
                                    painter = painterResource(R.drawable.tick_icon),
                                    contentDescription = "Selected",
                                    tint = Color.Black
                                )
                            }
                        }

                        HorizontalDivider(
                            color = Color(0xFFB1B2B2),
                            thickness = 0.5.dp
                        )
                    }
                }
            }
        }
    }
}
