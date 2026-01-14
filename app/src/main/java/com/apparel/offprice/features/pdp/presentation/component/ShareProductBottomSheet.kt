package com.apparel.offprice.features.pdp.presentation.component

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apparel.offprice.R
import com.apparel.offprice.common.component.noRippleClickable
import com.apparel.offprice.common.theme.borderColor
import com.apparel.offprice.common.theme.inputTextColor
import com.apparel.offprice.common.theme.loginButtonColor
import com.apparel.offprice.common.theme.saleCardColor
import com.apparel.offprice.common.theme.secondaryBlue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareProductBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    productLink: String = "Adidas.Product/Share-Link"
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null, // we use custom header
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {

            // ------- Header -------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.share_product),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(700)),
                    fontSize = 16.sp,
                    color = saleCardColor
                )

                Icon(
                    painter = painterResource(R.drawable.close_login),
                    contentDescription = "Close",
                    tint = secondaryBlue,
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable { onDismiss() }
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            HorizontalDivider(modifier = Modifier.fillMaxWidth().height(1.dp).background(borderColor))

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.share_link_via),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight(500),
                    fontSize = 12.sp
                ),
                color = loginButtonColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ------- Social Buttons -------
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ShareIcon(painterResource(R.drawable.share_whatsapp), "WhatsApp")
                ShareIcon(painterResource(R.drawable.share_fb), "Facebook")
                ShareIcon(painterResource(R.drawable.share_sms), "Email")
                ShareIcon(painterResource(R.drawable.share_pinterest), "Pinterest")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(com.apparel.offprice.R.string.or_copy_link),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500)),
                fontSize = 12.sp,
                color = loginButtonColor
            )

            Spacer(modifier = Modifier.height(10.dp))

            val context = LocalContext.current
            val clipboardManager =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            // ------- Copy Link UI -------
            OutlinedTextField(
                value = productLink,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium
                    .copy(fontSize = 12.sp, fontWeight = FontWeight(500), color = inputTextColor),
                trailingIcon = {
                    Image(
                        painter = painterResource(R.drawable.copy_linkimg),
                        modifier = Modifier
                            .size(26.dp)
                            .clickable {
                                val clip = ClipData.newPlainText(
                                    "product_link",
                                    productLink
                                )
                                clipboardManager.setPrimaryClip(clip)

                                Toast.makeText(
                                    context,
                                    "Copied to clipboard",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                        contentDescription = "Copy"
                    )
                },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    errorBorderColor = androidx.compose.ui.graphics.Color.Transparent,
                    disabledBorderColor = borderColor,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun ShareIcon(icon: Painter, contentDescription: String) {
    Image(
        icon,
        contentDescription = contentDescription,
        modifier = Modifier.size(45.dp)
    )
}
