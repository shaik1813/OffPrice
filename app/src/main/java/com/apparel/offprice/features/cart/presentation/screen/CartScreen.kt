package com.apparel.offprice.features.cart.presentation.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.cart.data.creditsData
import com.apparel.offprice.features.cart.data.priceData
import com.apparel.offprice.features.cart.presentation.component.CartBottomView
import com.apparel.offprice.features.cart.presentation.component.CartEmptyScreen
import com.apparel.offprice.features.cart.presentation.component.CartItemCard
import com.apparel.offprice.features.cart.presentation.component.CouponOfferBottomSheet
import com.apparel.offprice.features.cart.presentation.component.DeleteConfirmationDialog
import com.apparel.offprice.features.cart.presentation.component.FreeShipCard
import com.apparel.offprice.features.cart.presentation.component.PaymentCard
import com.apparel.offprice.features.cart.presentation.component.QuantityBottomSheet
import com.apparel.offprice.features.cart.presentation.component.UseCreditsCard
import com.apparel.offprice.features.coupon.presentation.component.TermsAndConditionsDialog
import com.apparel.offprice.features.pdp.presentation.component.CouponCard
import features.cart.presentation.component.PriceSummaryCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val (state, event, effect) = use(viewModel = viewModel)

    if (state.isCouponOfferSheet) {
        CouponOfferBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onClose = {
                event(CartContract.UiEvent.onCloseBottomSheetOffer)
            }, onApply = {
                event(CartContract.UiEvent.onCloseBottomSheetOffer)
                event(CartContract.UiEvent.OnCouponChanged(it))
                event(CartContract.UiEvent.OnApplyCoupon(it))
            },
            onOfferDetailClick = {
                event(CartContract.UiEvent.OnOpenOfferDetailDialog)
            })
    }

    if (state.isDeleteCartDialog) {
        DeleteConfirmationDialog(
            onDelete = {
                event(CartContract.UiEvent.onCloseDeleteCartConfirm)
                event(CartContract.UiEvent.OnDeleteCartItem(state.deleteItemId!!))
            },
            onCancel = {
                event(CartContract.UiEvent.onCloseDeleteCartConfirm)
            },
            onDismiss = {
                event(CartContract.UiEvent.onCloseDeleteCartConfirm)
            }
        )
    }

    if (state.isQuantitySheet) {
        QuantityBottomSheet(
            state.selectedQuantity,
            onSelectItem = {
                event(CartContract.UiEvent.OnQuantitySelected(it))
            },
            onDismiss = {
                event(CartContract.UiEvent.OnCloseQuantitySheet)
            },
            onSumbit = {
                event(CartContract.UiEvent.OnSubmitQuantity)
                event(CartContract.UiEvent.OnCloseQuantitySheet)
            }
        )
    }

    if (state.isOfferDialog) {
        TermsAndConditionsDialog(onDismiss = {
            event(CartContract.UiEvent.OnCloseOfferDetailDialog)
        })
    }

    effect.CollectInLaunchedEffect {
        when(it){
            is CartContract.UiEffect.ShowMessage -> {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        bottomBar = {
            if(!state.isCartEmpty) {
                CartBottomView(
                    onCheckOutClick = {}
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            CartToolBar()

            ElevationBottom()

            if (state.isCartEmpty) {
                CartEmptyScreen(onStartShoppingClick = {})
            } else {

                Spacer(modifier = Modifier.size(20.dp))

                LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(items = state.cartItems,  key = { it -> it.id }) {  item ->
                        CartItemCard(
                            item,
                            selectQuantity = {
                                event(CartContract.UiEvent.OnOpenQuantitySheet(it))
                            },
                            onDelete = {
                                event(CartContract.UiEvent.onOpenDeleteCartConfirm(it))
                            }
                        )
                    }

                    item {
                        FreeShipCard()
                    }

                    item {
                        CouponCard(
                            state,
                            OnApply = {
                                event(CartContract.UiEvent.OnApplyToggleClick)
                            },
                            OnCouponChange = {
                                event(CartContract.UiEvent.OnCouponChanged(it))
                            },
                            OfferClick = {
                                event(CartContract.UiEvent.onOpenBottomSheetOffer)
                            })
                    }

                    /*   item{   it will be phase-2
                           GiftCard()
                       }*/

                    item {
                        PaymentCard()
                    }

                    item {
                        UseCreditsCard(
                            creditsdata = creditsData,
                            caPointsChecked = state.isCheckedClub,
                            storeCreditsChecked = state.isCheckedStore,
                            onCaPointsToggle = { event(CartContract.UiEvent.OnToggleCheckedClubPoint) },
                            onStoreCreditsToggle = { event(CartContract.UiEvent.OnToggleCheckedStorePoint) })
                    }

                    item {
                        PriceSummaryCard(
                            isOpenShipFee = state.isOpenShipFee,
                            priceData = priceData,
                            OnShipFeeClick = {
                                event(CartContract.UiEvent.OnShipFeeClick)
                            })
                    }
                }
            }
        }
    }
}



@Composable
fun ElevationBottom() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFCCCCCC))
    )
}

@Composable
fun CartToolBar() {
    Row(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = stringResource(R.string.cart_items),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            fontSize = 14.sp
        )
    }
}