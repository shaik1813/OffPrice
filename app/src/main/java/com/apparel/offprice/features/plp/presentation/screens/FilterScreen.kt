package com.apparel.offprice.features.plp.presentation.screens


import com.apparel.offprice.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.IconBackgroundColor
import com.apparel.offprice.common.theme.backgroundColor
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.lineColor

@Composable
fun FilterScreen(
    onClose: () -> Unit,
    onApply: () -> Unit,
    onClearAll: () -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("categories") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // ───────── HEADER: FILTER + Clear All ─────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.filter_label),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.clear_all_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.clickable { onClearAll() }
            )
        }

        HorizontalDivider()

        // ───────── BODY: Left menu + right content ─────────
        Row(
            modifier = Modifier
                .weight(1f) // body takes all remaining height
                .fillMaxWidth()
        ) {

            // LEFT FILTER MENU
            LazyColumn(
                modifier = Modifier
                    .width(150.dp)
                    .background(IconBackgroundColor)
                    .padding(vertical = 8.dp)
            ) {
                items(filterMenuItems) { item ->
                    val isSelected = item.id == selectedFilter

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) Color.Black else Color.Transparent)
                            .clickable { selectedFilter = item.id }
                            .padding(vertical = 12.dp, horizontal = 8.dp)
                    ) {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.titleSmall,
                            color = if (isSelected) Color.White else buttonBorderColor,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // RIGHT CONTENT
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {

                // Search only for Categories (like Figma)
                if (selectedFilter == "categories") {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Search for Clothing",
                            style = MaterialTheme.typography.bodySmall) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.icon_search),
                                contentDescription = null
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(IconBackgroundColor),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = backgroundColor,
                            unfocusedBorderColor = Color.LightGray
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

                when (selectedFilter) {
                    "categories" -> CategoryFilterList(sampleCategoryList)
                    "color" -> ColorFilterList(sampleColorList)
                    else -> Text(
                        text = "No UI implemented for '${selectedFilter}' yet",
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

        HorizontalDivider()

        // ───────── FOOTER BUTTONS ─────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // CLOSE button (outlined)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
                    .clickable { onClose() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "CLOSE",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            // VIEW PRODUCTS button (black filled)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black)
                    .clickable { onApply() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "VIEW PRODUCTS",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterScreenPreview(){
    FilterScreen(onClose = {}, onApply = {}, onClearAll = {})
}