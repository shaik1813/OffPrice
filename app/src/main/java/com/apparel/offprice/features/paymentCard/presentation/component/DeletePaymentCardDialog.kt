package com.apparel.offprice.features.paymentCard.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DeleteOptionDialog


@Composable
fun DeletePaymentCardDialog(
    onDismiss: () -> Unit,
    onDeleteCard: () -> Unit
){
    DeleteOptionDialog(
        message = stringResource(R.string.text_delete_payment_card),
        onNegativeButtonClicked = { onDismiss() },
        onPositiveButtonClicked = { onDeleteCard() }
    )
}