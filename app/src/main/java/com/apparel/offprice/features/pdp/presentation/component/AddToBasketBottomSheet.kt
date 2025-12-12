package com.apparel.offprice.features.pdp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToBasketBottomSheet(
    sheetState: SheetState,
    onContinue: () -> Unit,
    onGoToBag: () -> Unit,
    onDismiss: () -> Unit
) {

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        dragHandle = null,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Top Success Icon
                Icon(
                    painter = painterResource(R.drawable.cart_tick_icon),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 10.dp).size(38.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Success Text
                Text(
                    stringResource(com.apparel.offprice.R.string.success_add_bag),
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(30.dp))
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // Outline Button
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp),
                    onClick = onContinue,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, loginButtonColor),

                ) {
                    Text(
                        stringResource(com.apparel.offprice.R.string.continue_shopping),
                        fontSize = 10.sp,
                        color = loginButtonColor,
                        textAlign = TextAlign.Center
                    )
                }

                // Black Button
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp),
                    onClick = onGoToBag,
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = loginButtonColor,
                        contentColor = Color.White
                    )
                ) {
                    Text(stringResource(com.apparel.offprice.R.string.go_to_bag),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center)
                }
            }

        }
    }
}
