package com.apparel.offprice.features.cart.presentation.screen

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.cart.presentation.component.CartItemCard
import com.apparel.offprice.features.cart.presentation.component.CouponOfferBottomSheet
import com.apparel.offprice.features.cart.presentation.component.DeleteConfirmationDialog
import com.apparel.offprice.features.cart.presentation.component.FreeShipCard
import com.apparel.offprice.features.cart.presentation.component.GiftCard
import com.apparel.offprice.features.cart.presentation.component.PaymentCard
import com.apparel.offprice.features.pdp.presentation.component.AddToBasketBottomSheet
import com.apparel.offprice.features.pdp.presentation.component.CouponCard
import com.apparel.offprice.features.pdp.presentation.screen.PDPContract


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(viewModel: CartViewModel = hiltViewModel()) {

    var (state, event, effect) = use(viewModel = viewModel)

    if (state.isCouponOfferSheet) {
        CouponOfferBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onClose = {
            event(CartContract.UiEvent.onCloseBottomSheetOffer)
        }, onApply = {
            event(CartContract.UiEvent.onCloseBottomSheetOffer)
        })
    }


    if(state.isDeleteCartDialog){
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


    Column(modifier = Modifier.fillMaxSize()) {

        CartToolBar()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .shadow(1.dp)
                .background(Color.Transparent)
        )

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
                    image = painterResource(id = R.drawable.colorimg),
                    onDelete = {
                        event(CartContract.UiEvent.onOpenDeleteCartConfirm)
                    }
                )
            }

            item{
                FreeShipCard()
            }

            item{
                CouponCard(OfferClick = {
                      event(CartContract.UiEvent.onOpenBottomSheetOffer)
                })
            }

            item{
                GiftCard()
            }

            item{
                PaymentCard()
            }
        }



    }

}


@Composable
fun CartToolBar() {
    Row(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.back_icon),
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = stringResource(R.string.cart_items))
    }
}