package com.apparel.offprice.features.storeCredit.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBar
import com.apparel.offprice.common.theme.buttonBorderColor
import com.apparel.offprice.common.theme.nonreturnTxtColor
import com.apparel.offprice.common.theme.offerTextColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.storeCredit.data.CreditType
import com.apparel.offprice.features.storeCredit.data.StoreCreditFilter
import com.apparel.offprice.features.storeCredit.data.StoreCreditTransaction
import com.apparel.offprice.features.storeCredit.presentation.component.CreditSummaryCard
import com.apparel.offprice.features.storeCredit.presentation.component.FilterChip

@Composable
fun StoreCreditScreen(
    viewModel: StoreCreditViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            StoreCreditContract.UiEffect.OnNavigateToBack -> {
                onNavigateBack()
            }
        }
    }
    StoreCreditContent(
        state = state,
        onBack = { event.invoke(StoreCreditContract.UiEvent.OnBackPressed) },
        onFilterSelected = { filter ->
            event.invoke(StoreCreditContract.UiEvent.OnFilterSelected(filter))
        },
    )
}


@Composable
private fun StoreCreditContent(
    state: StoreCreditContract.UiState,
    onBack: () -> Unit,
    onFilterSelected: (StoreCreditFilter) -> Unit,
) {
    val transactions = remember(state.selectedFilter, state.transactions) {
        when (state.selectedFilter) {
            StoreCreditFilter.ALL -> state.transactions
            StoreCreditFilter.RECEIVED -> state.transactions.filter { it.type == CreditType.RECEIVED }
            StoreCreditFilter.USED -> state.transactions.filter { it.type == CreditType.USED }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .background(MaterialTheme.colorScheme.surface),
    ) {
        DefaultTopAppBar(
            title = stringResource(R.string.label_store_credit),
            onBackPressed = { onBack }
        )
        HorizontalDivider(thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))
        SummaryRow(state = state)
        Spacer(modifier = Modifier.height(16.dp))
        StoreCreditHistoryCard(
            transactions = transactions,
            selectedFilter = state.selectedFilter,
            onFilterSelected = onFilterSelected,
        )
    }
}

@Composable
private fun SummaryRow(
    state: StoreCreditContract.UiState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CreditSummaryCard(
            title = stringResource(R.string.store_credit_total_balance),
            amount = state.totalBalance,
            drawable = R.drawable.icon_total_balance,
            backgroundColor = Color(0x1A0CB53F),
            borderColor = Color(0x4D0CB53F),
            accentColor = Color(0xFF0CB53F),
            modifier = Modifier.weight(1f),
        )
        CreditSummaryCard(
            title = stringResource(R.string.store_credit_credits_received),
            amount = state.totalReceived,
            drawable = R.drawable.icon_credit_received,
            backgroundColor = Color(0x1AF79F49),
            borderColor = Color(0x80F79F49),
            accentColor = Color(0xFFF79F49),
            modifier = Modifier.weight(1f),
        )
        CreditSummaryCard(
            title = stringResource(R.string.store_credit_credits_used),
            amount = state.totalUsed,
            drawable = R.drawable.icon_credit_used,
            backgroundColor = Color(0x1A49A1F7),
            borderColor = Color(0x4D49A1F7),
            accentColor = Color(0xFF49A1F7),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun StoreCreditHistoryCard(
    transactions: List<StoreCreditTransaction>,
    selectedFilter: StoreCreditFilter,
    onFilterSelected: (StoreCreditFilter) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = Color(0xFFE6E6E6)),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            FilterRow(
                selectedFilter = selectedFilter,
                onFilterSelected = onFilterSelected,
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                itemsIndexed(
                    transactions,
                    key = { _, item -> item.orderId }) { index, transaction ->
                    StoreCreditTransactionRow(transaction = transaction)
                    if (index < transactions.lastIndex) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterRow(
    selectedFilter: StoreCreditFilter,
    onFilterSelected: (StoreCreditFilter) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, buttonBorderColor.copy(alpha = 0.4f)),
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            FilterChip(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.store_credit_filter_all),
                selected = selectedFilter == StoreCreditFilter.ALL,
                onClick = { onFilterSelected(StoreCreditFilter.ALL) },
            )
            FilterChip(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.store_credit_filter_received),
                selected = selectedFilter == StoreCreditFilter.RECEIVED,
                onClick = { onFilterSelected(StoreCreditFilter.RECEIVED) },
            )
            FilterChip(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.store_credit_filter_used),
                selected = selectedFilter == StoreCreditFilter.USED,
                onClick = { onFilterSelected(StoreCreditFilter.USED) },
            )
        }
    }
}


@Composable
private fun StoreCreditTransactionRow(
    transaction: StoreCreditTransaction,
) {
    val isReceived = transaction.type == CreditType.RECEIVED
    val statusColor = if (isReceived) Color(0xFF4CAF50) else offerTextColor
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(color = Color(0xFFF7F2E5))
                        .padding(horizontal = 8.dp, vertical = 7.dp),
                ) {
                    Text(
                        text = stringResource(
                            R.string.store_credit_order_label,
                            transaction.orderId,
                        ),
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 12.sp),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.store_credit_refunded_on),
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = transaction.refundedDate,
                        style = MaterialTheme.typography.titleSmall.copy(fontSize = 12.sp),
                        color = Color(0xFF4CAF50),
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = if (isReceived) {
                        stringResource(
                            R.string.store_credit_amount_positive,
                            transaction.amount,
                        )
                    } else {
                        stringResource(
                            R.string.store_credit_amount_negative,
                            transaction.amount,
                        )
                    },
                    style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                    color = statusColor,
                )
                Text(
                    text = if (isReceived) {
                        stringResource(R.string.store_credit_filter_received)
                    } else {
                        stringResource(R.string.store_credit_filter_used)
                    },
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                    color = nonreturnTxtColor,
                )
            }
        }
    }
}