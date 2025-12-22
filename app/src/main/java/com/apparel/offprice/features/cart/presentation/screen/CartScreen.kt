package com.apparel.offprice.features.cart.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
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
import com.apparel.offprice.features.checkout.presentation.screens.ShippingAddressScreen
import com.apparel.offprice.features.coupon.presentation.component.TermsAndConditionsDialog
import com.apparel.offprice.features.pdp.presentation.component.CouponCard
import com.apparel.offprice.features.pdp.presentation.component.ElevatedLine
import features.cart.presentation.component.PriceSummaryCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(onCheckoutClick : () -> Unit,viewModel: CartViewModel = hiltViewModel()) {

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val context = LocalContext.current

    var (state, event, effect) = use(viewModel = viewModel)

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
            screenHeightDp,
            onSelectItem = {

            },
            onDismiss = {
                event(CartContract.UiEvent.OnCloseQuantitySheet)
            },
            onSumbit = {
                event(CartContract.UiEvent.OnCloseQuantitySheet)
            }
        )
    }

    if(state.isOfferDialog){
        TermsAndConditionsDialog(onDismiss = {
            event(CartContract.UiEvent.OnCloseOfferDetailDialog)
        })
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CartContract.UiEffect.ShowMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }





    Box() {
        Column(modifier = Modifier.fillMaxSize()) {

            CartToolBar()

            ElevationBottom()

            if(state.isCartEmpty) {
                CartEmptyScreen(onStartShoppingClick = {})
                return
            }

            Spacer(modifier = Modifier.size(20.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(3) {
                    CartItemCard(
                        brand = "ADIDAS",
                        title = "Printed Shirt With Crew Neck And Short Sleeves",
                        price = "฿ 35.00",
                        oldPrice = "฿ 496.00",
                        color = "Blue",
                        size = "L",
                        qty = "01",
                        deliveryText = "DELIVERY BY 06 NOV, THU",
                        image = painterResource(id = R.drawable.product_item_1),
                        selectQuantity = {
                            event(CartContract.UiEvent.OnOpenQuantitySheet)
                        },
                        onDelete = {
                            event(CartContract.UiEvent.onOpenDeleteCartConfirm)
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
                        creditsData,
                        caPointsChecked = state.isCheckedClub,
                        storeCreditsChecked = state.isCheckedStore,
                        onCaPointsToggle = { event(CartContract.UiEvent.OnToggleCheckedClubPoint) },
                        onStoreCreditsToggle = { event(CartContract.UiEvent.OnToggleCheckedStorePoint) })
                }

                item {
                    PriceSummaryCard(
                        state.isOpenShipFee, priceData,
                        OnShipFeeClick = {
                            event(CartContract.UiEvent.OnShipFeeClick)
                        })
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {

            ElevatedLine()

            CartBottomView(
                onCheckOutClick = {
                    onCheckoutClick()
                }
            )

        }
    }


}


@Composable
fun ElevationBottom(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .shadow(1.dp)
            .background(Color.Transparent)
    )
}

@Composable
fun CartToolBar() {
    Row(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        /*Image(
            painter = painterResource(R.drawable.back_icon),
            contentDescription = null
        )*/

        Spacer(modifier = Modifier.size(16.dp))

        Text(text = stringResource(R.string.cart_items),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            fontSize = 14.sp)
    }
}