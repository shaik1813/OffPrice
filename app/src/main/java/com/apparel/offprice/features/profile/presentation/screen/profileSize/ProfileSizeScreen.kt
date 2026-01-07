package com.apparel.offprice.features.profile.presentation.screen.profileSize

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.BottomDoubleActionButton
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.theme.backgroundColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.primaryColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.common.theme.secondaryColor
import com.apparel.offprice.common.theme.tertiaryColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSizeScreen(
    onNavigateToBack: () -> Unit,
    viewModel: ProfileSizeViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            ProfileSizeContract.UiEffect.OnBackPressed -> {
                onNavigateToBack()
            }

            ProfileSizeContract.UiEffect.OnSaveSuccess -> {
                onNavigateToBack()
            }

            ProfileSizeContract.UiEffect.OnClearAllSuccess -> {
                // Handle clear all success if needed
            }
        }
    }

    Scaffold(
        topBar = {
            DefaultTopAppBar(title = stringResource(R.string.label_mysize).uppercase()) {
                event.invoke(ProfileSizeContract.UiEvent.OnBackPressed)
            }
        },
        bottomBar = {
            BottomDoubleActionButton(
                leftButtonText = stringResource(R.string.label_clear_all).uppercase(),
                rightButtonText = stringResource(R.string.label_save).uppercase(),
                onLeftClick = { event.invoke(ProfileSizeContract.UiEvent.OnClearAll) },
                onRightClick = { event.invoke(ProfileSizeContract.UiEvent.OnSave) }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        contentWindowInsets = WindowInsets(bottom = 0),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                // Gender Category Chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    GenderChip(
                        label = ProfileSizeContract.Gender.MEN.toString(),
                        image = R.drawable.product_item_1,
                        isSelected = state.selectedGender == ProfileSizeContract.Gender.MEN,
                        onClick = {
                            event.invoke(
                                ProfileSizeContract.UiEvent.OnGenderSelected(
                                    ProfileSizeContract.Gender.MEN
                                )
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                    GenderChip(
                        label = ProfileSizeContract.Gender.WOMEN.toString(),
                        image = R.drawable.image_size_women,
                        isSelected = state.selectedGender == ProfileSizeContract.Gender.WOMEN,
                        onClick = {
                            event.invoke(
                                ProfileSizeContract.UiEvent.OnGenderSelected(
                                    ProfileSizeContract.Gender.WOMEN
                                )
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                    GenderChip(
                        label = ProfileSizeContract.Gender.KIDS.toString(),
                        image = R.drawable.image_size_kids,
                        isSelected = state.selectedGender == ProfileSizeContract.Gender.KIDS,
                        onClick = {
                            event.invoke(
                                ProfileSizeContract.UiEvent.OnGenderSelected(
                                    ProfileSizeContract.Gender.KIDS
                                )
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Make Primary Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.label_make_primary),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                        ),
                        color = saleCardColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Switch(
                        checked = state.isPrimary,
                        onCheckedChange = {
                            event.invoke(ProfileSizeContract.UiEvent.OnTogglePrimary)
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = primaryColor,
                            checkedTrackColor = Color(0xFFB0B0B0),
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color(0xFFB0B0B0)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Size Selection Cards
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    SizeSelectionCard(
                        title = stringResource(R.string.label_clothing_size),
                        selectedSize = state.selectedClothingSize,
                        sizeList = state.clothingSize,
                        onClick = { size ->
                            event.invoke(ProfileSizeContract.UiEvent.OnClothingSizeSelected(size))
                        }
                    )

                    SizeSelectionCard(
                        title = stringResource(R.string.label_denim_size),
                        selectedSize = state.selectedDenimSize,
                        sizeList = state.denimSize,
                        onClick = { size ->
                            event.invoke(ProfileSizeContract.UiEvent.OnDenimSizeSelected(size))
                        }
                    )

                    SizeSelectionCard(
                        title = stringResource(R.string.label_shoe_size),
                        selectedSize = state.selectedShoeSize,
                        sizeList = state.shoeSize,
                        onClick = { size ->
                            event.invoke(ProfileSizeContract.UiEvent.OnShoeSizeSelected(size))
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun GenderChip(
    label: String,
    image: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(36.dp)
            .background(
                color = Color(0xFFFDE8EA),
                shape = RoundedCornerShape(6.dp)
            )
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 0.6.dp,
                        color = secondaryColor,
                        shape = RoundedCornerShape(6.dp)
                    )
                } else {
                    Modifier
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp
                ),
                color = saleCardColor,
                textAlign = TextAlign.Center
            )
            Image(
                painterResource(image),
                contentDescription = label,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
fun SizeSelectionCard(
    title: String,
    selectedSize: List<String>,
    sizeList: List<String>,
    onClick: (String) -> Unit
) {
    var showSizeSheet by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showSizeSheet = !showSizeSheet
            },
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEDEDED)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFFE6E6E6)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                    ),
                    color = primaryColor
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                ) {
                    Text(
                        text = stringResource(R.string.label_selected_size),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 10.sp,
                        ),
                        color = inputTextColor
                    )
                    if (selectedSize.isNotEmpty()) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            selectedSize.forEach {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        letterSpacing = (-0.2).sp
                                    ),
                                    color = primaryColor,
                                )
                            }
                        }
                    }
                }
            }
            Icon(
                imageVector = if (showSizeSheet) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                tint = loginButtonColor,
                contentDescription = null
            )
        }
    }
    AnimatedVisibility(visible = showSizeSheet) {
        SizeSelectionBottomSheet(
            selectedSize = selectedSize,
            sizeList = sizeList,
            onSizeSelected = { size ->
                onClick(size)
            }
        )
    }

}

@Composable
fun SizeSelectionBottomSheet(
    selectedSize: List<String>,
    sizeList: List<String>,
    onSizeSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(sizeList) { size ->
                SizeChip(
                    size = size,
                    selectedSize = selectedSize,
                    onClick = { onSizeSelected(size) }
                )
            }
        }
    }
}

@Composable
fun SizeChip(
    size: String,
    selectedSize: List<String>,
    onClick: () -> Unit
) {
    val isSelected = selectedSize.contains(size)
    Card(
        modifier = Modifier
            .width(51.dp)
            .height(32.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) primaryColor else backgroundColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFFE6E6E6)
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = size,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp
                ),
                color = if (isSelected) backgroundColor else primaryColor,
                textAlign = TextAlign.Center
            )
        }
    }
}
