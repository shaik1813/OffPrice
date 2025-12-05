package com.apparel.offprice.features.wishlist.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.home.PLPScreenShimmer
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.plp.presentation.screens.ProductCardItems
import com.apparel.offprice.features.wishlist.presentation.component.EmptyWishListScreen
import com.apparel.offprice.features.wishlist.presentation.component.RemoveWishListDialog
import com.apparel.offprice.features.wishlist.presentation.component.SuccessfullyAddedToBasketBottomSheet
import com.apparel.offprice.features.wishlist.presentation.component.WishList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListScreen(
    onNavigateToBack: () -> Unit,
    onStartShoppingClicked: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToPDP: (ProductCardItems) -> Unit,
    viewModel: WishListViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            WishListContract.UiEffect.OnBackPressed -> {
                onNavigateToBack()
            }

            WishListContract.UiEffect.OnStartShopping -> {
                onStartShoppingClicked()
            }

            is WishListContract.UiEffect.ShowError -> {

            }

            is WishListContract.UiEffect.OnNavigateToPDP -> {
                onNavigateToPDP(it.product)
            }

            WishListContract.UiEffect.OnNavigateToCart -> {
                onNavigateToCart()
            }
        }
    }
    if (state.isLoading){
        PLPScreenShimmer()
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.safeDrawing.asPaddingValues())
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = if (state.wishListCount == 0) stringResource(R.string.label_wishlist_without_count) else stringResource(
                            R.string.label_wishlist,
                            state.wishListCount
                        ),
                        style = MaterialTheme.typography.titleMedium.copy(letterSpacing = 0.2.sp),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.back_icon),
                        contentDescription = "Arrow back",
                        modifier = Modifier
                            .clickable {
                                event.invoke(WishListContract.UiEvent.OnBackPressed)
                            }
                    )
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
            if (state.wishListCount == 0) {
                //empty Wish List View
                EmptyWishListScreen(onStartShoppingClicked = {
                    event.invoke(WishListContract.UiEvent.OnStartShopping)
                })
            } else {
                //show WishList View
                WishList(
                    wishlistItem = state.wishListItems,
                    addToCartItem = { product ->
                        event.invoke(WishListContract.UiEvent.OnAddToBagClicked(product))
                    },
                    onNavigateToPDP = { product ->
                        event.invoke(WishListContract.UiEvent.OnNavigateToPDP(product))
                    },
                    onWishListClicked = { product ->
                        viewModel.openRemoveWishListDialog(productCardItems = product)
                    }
                )
            }

            if (state.isWishListRemovalDialog) {
                RemoveWishListDialog(
                    onDismiss = {
                        viewModel.dismissDialog()
                    },
                    onRemoveWishList = {
                        event.invoke(WishListContract.UiEvent.RemoveWishList(productId = ""))
                    }
                )
            }

            if (state.isAddToBagDialog) {
                //Show the BottomSheet
                SuccessfullyAddedToBasketBottomSheet(
                    onDismiss = {
                        viewModel.dismissDialog()
                    },
                    onContinueShoppingClicked = {
                        event.invoke(WishListContract.UiEvent.OnStartShopping)
                    },
                    onGoToBagClicked = {
                        event.invoke(WishListContract.UiEvent.OnNavigateToCart)
                    }
                )
            }
        }
    }
}




