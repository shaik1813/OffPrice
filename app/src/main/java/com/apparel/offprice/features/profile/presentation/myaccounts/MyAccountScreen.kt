package com.apparel.offprice.features.profile.presentation.myaccounts

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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.takeInitials
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.authentication.presentation.screen.ForgotDialog
import com.apparel.offprice.features.authentication.presentation.screen.LoginDialog
import com.apparel.offprice.features.authentication.presentation.screen.OTPVerifyDialog
import com.apparel.offprice.features.authentication.presentation.screen.SignupDialog
import com.apparel.offprice.features.home.data.model.MyAccountItems
import com.apparel.offprice.features.home.data.model.accountItems
import com.apparel.offprice.features.home.presentation.component.CircularProgressbar
import com.apparel.offprice.features.home.presentation.component.CountrySelectionBottomSheet
import com.apparel.offprice.features.home.presentation.component.LanguageSelectionBottomSheet
import com.apparel.offprice.routes.AppScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAccountScreen(
    onNavigateToSearch: () -> Unit,
    onNavigateToWishlist: () -> Unit,
    onItemClick: (MyAccountItems) -> Unit,
    isGuestUser: Boolean = true,
    viewModel: MyAccountViewModel = hiltViewModel()
) {
    var showCountrySheet by remember { mutableStateOf(false) }
    var showLanguageSheet by remember { mutableStateOf(false) }

    var showLoginDialog by remember { mutableStateOf(false) }
    var showSignupDialog by remember { mutableStateOf(false) }
    var showForgotDialog by remember { mutableStateOf(false) }
    var showOtpDialog by remember { mutableStateOf(false) }


    val (state, event, effect) = use(viewModel = viewModel)

    if (showSignupDialog) {
        SignupDialog(onDismiss = { showSignupDialog = false })
    }

    if (showForgotDialog) {
        ForgotDialog(onDismiss = { showForgotDialog = false })
    }

    if (showOtpDialog) {
        OTPVerifyDialog(onDismiss = { showOtpDialog = false })
    }

    if (showLoginDialog) {
        LoginDialog({ showLoginDialog = false }, onItemClick = { appScreen ->
            when (appScreen) {
                is AppScreen.RegistrationScreen -> showSignupDialog = true
                is AppScreen.ForgetPasswordScreen -> showForgotDialog = true
                is AppScreen.OTPScreen -> showOtpDialog = true
                else -> {}
            }
        })
    }

    effect.CollectInLaunchedEffect {
        when (it) {
            is MyAccountContract.UiEffect.ShowError -> {}
            is MyAccountContract.UiEffect.AccountItemClick -> {
                onItemClick(it.item)
            }

            MyAccountContract.UiEffect.NavigateToLogin -> {
                showLoginDialog = true
            }

            MyAccountContract.UiEffect.NavigateToRegistration -> {
                showSignupDialog = true
            }

            MyAccountContract.UiEffect.NavigateToSearch -> {
                onNavigateToSearch()
            }

            MyAccountContract.UiEffect.NavigateToWishlist -> {
                onNavigateToWishlist()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.label_my_account).uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 4.dp)
                )
            },
            actions = {
                IconButton(
                    onClick = { event.invoke(MyAccountContract.UiEvent.NavigateToSearch) },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_search),
                        contentDescription = "Search",
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(
                    onClick = { event.invoke(MyAccountContract.UiEvent.NavigateToWishlist) },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_wishlist),
                        contentDescription = "Wishlist",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            windowInsets = WindowInsets(0, 0, 0, 0),
            modifier = Modifier
                .shadow(
                    elevation = 6.dp,
                    spotColor = Color.Gray
                ),
        )
        HorizontalDivider(thickness = 1.dp)

        Spacer(modifier = Modifier.height(20.dp))
        if (isGuestUser) {
            //Guest Flow
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.background),
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
        } else {
            //LoggedIn Flow
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(12.dp)
            ) {
                item {
                    UserProfileCard(
                        name = state.username,
                        email = state.userEmail,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                items(accountItems, key = { it.categoryId }) { item ->
                    AccountMenuItem(
                        title = stringResource(item.title),
                        icon = item.icon,
                        onClick = { event.invoke(MyAccountContract.UiEvent.AccountItemClick(item)) }
                    )
                    HorizontalDivider(color = Color(0xFFEAEAEA))
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                onClick = { showCountrySheet = true },
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
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
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        painter = painterResource(R.drawable.icon_arrow_down),
                        contentDescription = "Country"
                    )
                }
            }

            TextButton(
                onClick = { showLanguageSheet = true },
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
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
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        painter = painterResource(R.drawable.icon_arrow_down),
                        contentDescription = "Language"
                    )
                }
            }

            //Country BottomSheet
            if (showCountrySheet) {
                CountrySelectionBottomSheet(
                    countries = state.countryItemList,
                    initialSelected = state.countrySelected,
                    onSubmit = { selected ->
                        event.invoke(MyAccountContract.UiEvent.OnCountrySelected(selected))
                        showCountrySheet = false
                    },
                    onDismiss = { showCountrySheet = false }
                )
            }


            //Language BottomSheet
            if (showLanguageSheet) {
                LanguageSelectionBottomSheet(
                    languageList = state.languageItemList,
                    initialSelected = state.languageSelected,
                    onSubmit = { selected ->
                        event.invoke(MyAccountContract.UiEvent.OnLanguageSelected(selected))
                        showLanguageSheet = false
                    },
                    onDismiss = { showLanguageSheet = false }
                )
            }
        }
    }
}


@Composable
fun ActionButtonsBar(
    modifier: Modifier = Modifier,
    leftButtonText: String = "CANCEL",
    rightButtonText: String = "OK",
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = onLeftClick,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(
                text = leftButtonText,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Button(
            onClick = onRightClick,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = rightButtonText,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun UserProfileCard(
    name: String,
    email: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressbar(name = name.takeInitials())
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = email,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
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

@Preview(showBackground = true)
@Composable
fun MyAccountScreenPreview() {
    MyAccountScreen(onNavigateToSearch = {}, onNavigateToWishlist = {}, onNavigateToLogin = {}, onNavigateToRegistration = {}, onItemClick = {})
}


