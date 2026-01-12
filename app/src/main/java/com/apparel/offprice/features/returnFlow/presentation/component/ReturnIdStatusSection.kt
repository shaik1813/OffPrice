package com.apparel.offprice.features.returnFlow.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.surfaceColor
import com.apparel.offprice.features.returnFlow.data.ReturnStatus
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReturnIdStatusSection(
    returnId: String,
    requestedOn: LocalDate,
    status: ReturnStatus
) {

    val clipboardManager = LocalClipboardManager.current

    val (iconRes, text) = when (status) {
        ReturnStatus.REQUESTED -> R.drawable.green_approved_icon to "Return Requested"
        ReturnStatus.APPROVED -> R.drawable.green_approved_icon to "Approved"
        ReturnStatus.INITIATED -> R.drawable.yellow_initiated_icon to "Initiated"
    }

    Column(modifier = Modifier.padding(16.dp)) {

        Surface(shape = RoundedCornerShape(5.dp), color = surfaceColor) {
            Row(modifier = Modifier.padding(3.dp)) {
                Text(
                    text = "RETURN ID - $returnId",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(5.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.copy_icon), // your copy icon
                    contentDescription = "Copy return id",
                    modifier = Modifier
                        .padding(3.dp)
                        .clickable {
                            clipboardManager.setText(
                                AnnotatedString(returnId)
                            )
                        }
                )
            }

        }

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = stringResource(
                    R.string.label_requested_on,
                    requestedOn.toDisplayDate()
                ),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(16.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toDisplayDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
    return format(formatter)
}