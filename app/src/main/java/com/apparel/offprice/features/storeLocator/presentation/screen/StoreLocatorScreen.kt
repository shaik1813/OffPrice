package com.apparel.offprice.features.storeLocator.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.component.ExposedDropDownComponent
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.openDialer
import com.apparel.offprice.common.utils.openGoogleMapsNavigation
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.storeLocator.data.StoreListModel
import com.apparel.offprice.features.storeLocator.presentation.component.StoreCountryList
import com.apparel.offprice.features.storeLocator.presentation.component.StoreListItem

@Composable
fun StoreLocatorScreen(
    onNavigateBack: () -> Unit,
    viewModel: StoreLocatorViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)
    val context = LocalContext.current

    effect.CollectInLaunchedEffect {
        when (it) {
            StoreLocatorContract.UiEffect.OnBackPressed -> {
                onNavigateBack()
            }

            is StoreLocatorContract.UiEffect.OpenDialer -> {
                context.openDialer(phoneNumber = it.phone)
            }

            is StoreLocatorContract.UiEffect.OpenNavigation -> {
                context.openGoogleMapsNavigation(latitude = it.lat, longitude = it.lng)
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        topBar = {
            DefaultTopAppBar(title = stringResource(R.string.label_store_locator).uppercase()) {
                event.invoke(StoreLocatorContract.UiEvent.OnBackPressed)
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(Modifier.height(20.dp))
            if (state.isListOfStore) {
                ShowListOfStoreScreen(state, event)
            } else {
                ShowMapOfStoreScreen(state, event)
            }
        }
    }
}

@Composable
fun ShowListOfStoreScreen(
    state: StoreLocatorContract.UiState = StoreLocatorContract.UiState(),
    event: (StoreLocatorContract.UiEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        StoreCountryList(
            countryList = state.listOfCountry,
            selectedCountry = state.selectedCountryStore ?: state.listOfCountry.first(),
            onCountrySelected = {
                event.invoke(StoreLocatorContract.UiEvent.OnCountrySelected(it))
            }
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.label_list_of_store).uppercase(),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 14.sp
                ),
                color = inputTextColor
            )
            Text(
                text = stringResource(R.string.label_find_store_near_you),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                ),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    event.invoke(StoreLocatorContract.UiEvent.ToggleStoreView)
                }
            )
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.selectedCountryStore?.storeList.orEmpty(), key = { it.id }) { store ->
                StoreListItem(
                    store = store,
                    onCallStoreClicked = {
                        event.invoke(StoreLocatorContract.UiEvent.OnCallStore(store.phone))
                    },
                    onGetDirectionStoreClicked = {
                        event.invoke(StoreLocatorContract.UiEvent.OnStoreDirection(store))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMapOfStoreScreen(
    state: StoreLocatorContract.UiState = StoreLocatorContract.UiState(),
    event: (StoreLocatorContract.UiEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        ExposedDropDownComponent(
            categoriesList = listOf("UAE", "Saudi Arabia", "Yemen", "Bahrain", "Kuwait"),
            selectedCategory = state.country,
            placeholder = stringResource(R.string.label_select_country),
            enabled = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onCategorySelected = {
                event.invoke(StoreLocatorContract.UiEvent.OnCountryChanged(it))
            }
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                ExposedDropDownComponent(
                    categoriesList = listOf("Cairo", "Alexandria", "Giza", "Dubai", "Aswan", "Luxor"),
                    selectedCategory = state.city,
                    placeholder = stringResource(R.string.label_select_city),
                    enabled = true,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    onCategorySelected = {
                        event.invoke(StoreLocatorContract.UiEvent.OnCityChanged(it))
                    }
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                ExposedDropDownComponent(
                    categoriesList = listOf("Cairo", "Alexandria", "Giza", "Dubai", "Aswan", "Luxor"),
                    selectedCategory = state.area,
                    placeholder = stringResource(R.string.label_select_area),
                    enabled = true,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    onCategorySelected = {
                        event.invoke(StoreLocatorContract.UiEvent.OnAreaChanged(it))
                    }
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.label_off_price_fashion),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 14.sp
                ),
                color = inputTextColor
            )
            Text(
                text = stringResource(R.string.label_list_of_store),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                ),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    event.invoke(StoreLocatorContract.UiEvent.ToggleStoreView)
                }
            )
        }
        Spacer(Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surface)
        ){
            Image(
                painter = painterResource(id = R.drawable.google_map),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF6F6F6)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "RAMILI MALL",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp
                    )
                )
                Spacer(Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.home_outlined_icon),
                        contentDescription = "Location",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "United Arab Emirates - Dubai",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 12.sp
                        ),
                        color = nonreturnTxtColor
                    )
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_city_ranking),
                        contentDescription = "Location",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Al Rigga",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 12.sp
                        ),
                        color = nonreturnTxtColor
                    )
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.mdi_clock_outline_icon),
                        contentDescription = "Location",
                        tint = nonreturnTxtColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Open Until 10:00 AM",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 12.sp
                        ),
                        color = nonreturnTxtColor
                    )
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_phone),
                        contentDescription = "Location",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "+966541350586",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 12.sp
                        ),
                        color = Color(0xFF4CAF50)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {
                            event.invoke(StoreLocatorContract.UiEvent.OnCallStore("+966541350586"))
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.small
                            ),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(R.string.label_call_store),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 12.sp
                                ),
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.icon_phone),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = {
                            event.invoke(
                                StoreLocatorContract.UiEvent.OnStoreDirection(
                                    StoreListModel(
                                        id = "1",
                                        name = "Ramili Mall",
                                        phone = "+966541350586",
                                        location = "bahrain- a’ali",
                                        distanceKm = "3424.29 KM",
                                        openTime = "Open Until 10:00 AM",
                                        imageRes = R.drawable.image_men,
                                        latitude = 37.7749,
                                        longitude = -122.419
                                    )
                                )
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = MaterialTheme.shapes.small,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(R.string.label_get_direction),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 12.sp
                                )
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.icon_direction_arrow),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}