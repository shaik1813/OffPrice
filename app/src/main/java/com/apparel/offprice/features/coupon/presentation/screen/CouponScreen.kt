package com.apparel.offprice.features.coupon.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.coupon.data.BankCouponModel
import com.apparel.offprice.features.coupon.data.CouponModel
import com.apparel.offprice.features.coupon.presentation.component.BankCouponCard
import com.apparel.offprice.features.coupon.presentation.component.CouponItemCard
import com.apparel.offprice.features.coupon.presentation.component.TermsAndConditionsDialog


@Composable
fun CouponScreen(
    onNavigateBack: () -> Unit,
    viewModel: CouponViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    effect.CollectInLaunchedEffect {
        when (it) {
            CouponContract.UiEffect.OnNavigateBack -> {
                onNavigateBack()
            }

            is CouponContract.UiEffect.ShowError -> {

            }

            is CouponContract.UiEffect.CopyToClipboard -> {
                clipboardManager.setText(AnnotatedString(it.text))
                Toast.makeText(context, "Code Copied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        DefaultTopAppBar(title = stringResource(R.string.label_coupon)) {
            event.invoke(CouponContract.UiEvent.OnBackPressed)
        }
        HorizontalDivider()
        Spacer(modifier = Modifier.height(20.dp))
        CouponContent(
            coupons = state.coupons,
            bankCoupons = state.bankCoupons,
            onApplyCoupon = { code ->
                event.invoke(CouponContract.UiEvent.OnApplyCoupon(code))
            },
            onTermsAndConditionsClicked = {
                event.invoke(CouponContract.UiEvent.OnTermsAndConditionsClicked)
            },
            onCopyCode = {
                event.invoke(CouponContract.UiEvent.OnCopyCode(it))
            }
        )
        if (state.isTermsAndConditionsDialog) {
            TermsAndConditionsDialog(onDismiss = {
                event.invoke(CouponContract.UiEvent.OnCleared)
            })
        }
    }
}


@Composable
fun CouponContent(
    coupons: List<CouponModel>,
    bankCoupons: List<BankCouponModel>,
    isCodeApplied: Boolean = false,
    onApplyCoupon: (String) -> Unit,
    onTermsAndConditionsClicked: () -> Unit,
    onCopyCode: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(coupons) { coupon ->
                CouponItemCard(
                    coupon = coupon,
                    isCodeApplied = isCodeApplied,
                    onApplyClicked = { onApplyCoupon(coupon.code) },
                    onTermsClicked = { onTermsAndConditionsClicked() },
                    onCopyCode = { code -> onCopyCode(code) }
                )
            }
            item {
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = stringResource(R.string.bank_coupon_title).uppercase(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    color = Color(0xFF040707),
                )
            }
            items(bankCoupons) { bankCoupon ->
                BankCouponCard(
                    bankCoupon = bankCoupon,
                )
            }
        }
    }
}

