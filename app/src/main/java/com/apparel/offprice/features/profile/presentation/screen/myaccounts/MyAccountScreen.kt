package com.apparel.offprice.features.profile.presentation.screen.myaccounts

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.home.presentation.component.CountrySelectionBottomSheet
import com.apparel.offprice.features.home.presentation.component.LanguageSelectionBottomSheet
import com.apparel.offprice.features.profile.data.MyAccountItems
import com.apparel.offprice.features.profile.presentation.component.ActionButtonsBar
import com.apparel.offprice.features.profile.presentation.component.QuickActionItem
import com.apparel.offprice.features.profile.presentation.component.UserProfileCard
import com.apparel.offprice.routes.AppScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToWishlist: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onItemClick: (AppScreen) -> Unit,
    viewModel: MyAccountViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)



    effect.CollectInLaunchedEffect {
        when (it) {
            is MyAccountContract.UiEffect.ShowError -> {

            }

            is MyAccountContract.UiEffect.AccountItemClick -> {
                onItemClick(it.item)
            }

            MyAccountContract.UiEffect.NavigateToLogin -> {
                onNavigateToLogin()
            }

            MyAccountContract.UiEffect.NavigateToRegistration -> {
            }

            MyAccountContract.UiEffect.NavigateToSearch -> {
                onNavigateToSearch()
            }

            MyAccountContract.UiEffect.NavigateToWishlist -> {
                onNavigateToWishlist()
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.label_my_account).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },
                navigationIcon = {

                },
                actions = {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = borderColor,
                                shape = CircleShape
                            )
                            .clickable {
                                event.invoke(MyAccountContract.UiEvent.NavigateToSearch)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_search),
                            contentDescription = "Search",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = borderColor,
                                shape = CircleShape
                            )
                            .clickable {
                                event.invoke(MyAccountContract.UiEvent.NavigateToWishlist)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_wishlist),
                            contentDescription = "WishList",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                windowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.shadow(
                    elevation = 6.dp,
                    spotColor = Color.Black
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    onClick = {
                        event.invoke(MyAccountContract.UiEvent.OnCountryToggle)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = RectangleShape
                        ),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(state.countrySelected.countryFlagRound),
                            contentDescription = "Country"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = state.countrySelected.countryName,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            painter = painterResource(R.drawable.icon_arrow_down),
                            contentDescription = "Country"
                        )
                    }
                }

                TextButton(
                    onClick = {
                        event.invoke(MyAccountContract.UiEvent.OnLanguageToggle)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = RectangleShape
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.icon_language),
                            contentDescription = "Language",
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = state.languageSelected.display,
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            painter = painterResource(R.drawable.icon_arrow_down),
                            contentDescription = "Language"
                        )
                    }
                }

                //Country BottomSheet
                if (state.isCountryBottomSheetOpened) {
                    CountrySelectionBottomSheet(
                        countries = state.countryItemList,
                        initialSelected = state.countrySelected,
                        onSubmit = { selected ->
                            event.invoke(MyAccountContract.UiEvent.OnCountrySelected(selected))
                        },
                        onDismiss = {
                            event.invoke(MyAccountContract.UiEvent.OnCountryToggle)
                        }
                    )
                }


                //Language BottomSheet
                if (state.isLanguageBottomSheetOpened) {
                    LanguageSelectionBottomSheet(
                        languageList = state.languageItemList,
                        initialSelected = state.languageSelected,
                        onSubmit = { selected ->
                            event.invoke(MyAccountContract.UiEvent.OnLanguageSelected(selected))
                        },
                        onDismiss = {
                            event.invoke(MyAccountContract.UiEvent.OnLanguageToggle)
                        }
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isGuestUser) {
                GuestUserScreen { event.invoke(it) }
            } else {
                AccountUserScreen(state = state) { event.invoke(it) }
            }
        }
    }
}

@Composable
fun GuestUserScreen(
    event: (MyAccountContract.UiEvent) -> Unit
) {
    //Guest Flow
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(borderColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_user),
                contentDescription = "Profile",
                tint = Color.Gray,
                modifier = Modifier.size(70.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        ActionButtonsBar(
            modifier = Modifier
                .fillMaxWidth(),
            leftButtonText = stringResource(R.string.login_caps),
            rightButtonText = stringResource(R.string.label_register_caps),
            onLeftClick = {
                //Login Flow
                event.invoke(MyAccountContract.UiEvent.NavigateToLogin)
            },
            onRightClick = {
                //Registration Flow
                event.invoke(MyAccountContract.UiEvent.NavigateToRegistration)
            }
        )
    }
}


@Composable
fun AccountUserScreen(
    state: MyAccountContract.UiState,
    event: (MyAccountContract.UiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //LoggedIn Flow
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                UserProfileCard(
                    name = state.username,
                    email = state.userEmail,
                )
            }

            item {
                QuickActionsRow(
                    onMyOrdersClick = {
                        event.invoke(MyAccountContract.UiEvent.AccountItemClick(AppScreen.MyOrdersScreen))
                    },
                    onReturnsClick = {
                        event.invoke(MyAccountContract.UiEvent.AccountItemClick(AppScreen.ReturnsScreen))
                    },
                    onExchangeClick = {
                        event.invoke(MyAccountContract.UiEvent.AccountItemClick(AppScreen.ExchangeScreen))
                    }
                )
            }

            item {
                MyAccountSection(
                    title = stringResource(id = R.string.label_account_settings),
                    items = state.accountSettingItem,
                    onItemClick = { item ->
                        event.invoke(MyAccountContract.UiEvent.AccountItemClick(item.navigation))
                    }
                )
            }

            item {
                MyAccountSection(
                    title = stringResource(id = R.string.label_my_shopping),
                    items = state.myShoppingItem,
                    onItemClick = { item ->
                        event.invoke(MyAccountContract.UiEvent.AccountItemClick(item.navigation))
                    }
                )
            }

            item {
                MyAccountSection(
                    title = stringResource(id = R.string.label_about_and_support),
                    items = listOf(
                        MyAccountItems(
                            R.string.label_customer_support,
                            R.drawable.icon_info,
                            9,
                            AppScreen.StoreLocatorScreen
                        ),
                    ),
                    onItemClick = { item ->
                        event.invoke(MyAccountContract.UiEvent.AccountItemClick(item.navigation))
                    }
                )
            }
            item {
                MyAccountSection(
                    title = stringResource(id = R.string.label_support_and_others),
                    items = listOf(
                        MyAccountItems(
                            R.string.label_log_out,
                            R.drawable.icon_log_out,
                            1,
                            AppScreen.LogOutScreen
                        ),
                    ),
                    onItemClick = { item ->
                        event.invoke(MyAccountContract.UiEvent.AccountItemClick(item.navigation))
                    }
                )
            }
        }
    }
}


@Composable
fun QuickActionsRow(
    onMyOrdersClick: () -> Unit,
    onReturnsClick: () -> Unit,
    onExchangeClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickActionItem(
            title = stringResource(id = R.string.label_my_orders),
            icon = R.drawable.icon_my_order,
            onClick = onMyOrdersClick,
            modifier = Modifier.weight(1f)
        )
        QuickActionItem(
            title = stringResource(id = R.string.label_returns),
            icon = R.drawable.icon_returns,
            onClick = onReturnsClick,
            modifier = Modifier.weight(1f)
        )
        QuickActionItem(
            title = stringResource(id = R.string.label_exchange),
            icon = R.drawable.icon_exchange,
            onClick = onExchangeClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun MyAccountSection(
    title: String,
    items: List<MyAccountItems>,
    onItemClick: (MyAccountItems) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF626262),
                fontSize = 13.sp
            ),
            modifier = Modifier.padding(bottom = 14.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            border = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = borderColor
            )
        ) {
            items.forEachIndexed { index, item ->
                MyAccountSectionItem(
                    title = stringResource(id = item.title),
                    icon = item.icon,
                    onClick = { onItemClick(item) },
                    showDivider = index != items.lastIndex
                )
            }
        }
    }
}


@Composable
private fun MyAccountSectionItem(
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    showDivider: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontSize = 14.sp
                ),
                modifier = Modifier.weight(1f)
            )

            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_right),
                contentDescription = "Arrow Right",
                tint = Color(0xFF8A8A8A),
                modifier = Modifier.size(20.dp)
            )
        }
        if (showDivider) {
            HorizontalDivider(color = borderColor)
        }
    }
}

@Composable
fun AccountMenuItem(
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = title,
            tint = Color.Black,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(R.drawable.icon_arrow_right),
            contentDescription = "Arrow Right",
            tint = Color.Black
        )
    }
}


