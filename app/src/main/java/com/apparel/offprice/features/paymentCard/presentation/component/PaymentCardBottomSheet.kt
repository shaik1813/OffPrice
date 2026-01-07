package com.apparel.offprice.features.paymentCard.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.BottomSingleActionButton
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.lineColor
import com.apparel.offprice.common.utils.formatExpiryDate
import com.apparel.offprice.features.paymentCard.presentation.screen.PaymentCardContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentCardBottomSheet(
    state: PaymentCardContract.PaymentCardState,
    onEvent: (PaymentCardContract.UiEvent) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetMaxWidth = BottomSheetDefaults.SheetPeekHeight,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.label_add_debit_credit_card).uppercase(),
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            onDismiss()
                        }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.label_card_number),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = MaterialTheme.shapes.small)
                    .height(42.dp)
            ) {
                BasicTextField(
                    value = state.cardNumber,
                    onValueChange = { input ->
                        val raw = input.filter(Char::isDigit).take(16)
                        onEvent(PaymentCardContract.UiEvent.OnCardNumberChanged(raw))
                    },
                    textStyle = MaterialTheme.typography.bodySmall,
                    singleLine = true,
                    enabled = true,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxSize(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(
                                    width = 0.75.dp,
                                    color = borderColor,
                                    shape = MaterialTheme.shapes.small
                                )
                                .padding(horizontal = 12.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (state.cardNumber.isEmpty()) {
                                Text(
                                    text = "XXXX XXXX XXXX XXXX",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = lineColor
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.label_expiry_date),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .background(Color.White, shape = MaterialTheme.shapes.small)
                            .height(42.dp)
                    ) {
                        BasicTextField(
                            value = formatExpiryDate(state.expiryDate),
                            onValueChange = { input ->
                                val raw = input.filter(Char::isDigit).take(4)

                                // Validate month
                                val month = raw.take(2)
                                if (month.length == 2 && month.toIntOrNull() !in 1..12) return@BasicTextField
                                onEvent(PaymentCardContract.UiEvent.OnExpiryDateChanged(raw))
                            },
                            textStyle = MaterialTheme.typography.bodySmall,
                            singleLine = true,
                            enabled = true,
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .fillMaxSize(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .border(
                                            width = 0.75.dp,
                                            color = borderColor,
                                            shape = MaterialTheme.shapes.small
                                        )
                                        .padding(horizontal = 12.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    if (state.expiryDate.isEmpty()) {
                                        Text(
                                            text = "MM/YY",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = lineColor
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }

                }

                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.label_cvv),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .background(Color.White, shape = MaterialTheme.shapes.small)
                            .height(42.dp)
                    ) {
                        BasicTextField(
                            value = state.cvv,
                            onValueChange = { input ->
                                val raw = input.filter(Char::isDigit).take(3)
                                onEvent(PaymentCardContract.UiEvent.OnCVVChanged(raw))
                            },
                            textStyle = MaterialTheme.typography.bodySmall,
                            singleLine = true,
                            enabled = true,
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .fillMaxSize(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            visualTransformation = PasswordVisualTransformation(),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .border(
                                            width = 0.75.dp,
                                            color = borderColor,
                                            shape = MaterialTheme.shapes.small
                                        )
                                        .padding(horizontal = 12.dp),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    if (state.cvv.isEmpty()) {
                                        Text(
                                            text = stringResource(R.string.label_cvv),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = lineColor
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        innerTextField()
                                        Image(
                                            painter = painterResource(R.drawable.info_icon),
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }

        BottomSingleActionButton(
            title = stringResource(R.string.label_add_my_card),
            onButtonClicked = {
                onEvent(PaymentCardContract.UiEvent.OnSaveCardClicked)
            }
        )
    }
}