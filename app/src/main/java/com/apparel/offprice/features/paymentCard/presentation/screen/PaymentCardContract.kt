package com.apparel.offprice.features.paymentCard.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.paymentCard.data.PaymentCardModel

interface PaymentCardContract :
UnidirectionalViewModel<
        PaymentCardContract.UiState,
        PaymentCardContract.UiEvent,
        PaymentCardContract.UiEffect>{

    data class UiState(
        val isLoading: Boolean = false,
        val paymentCards: List<PaymentCardModel> = emptyList(),
        val isAddCardOpened : Boolean = false,
        val cardForm: PaymentCardState = PaymentCardState(),
        val isDeleteDialogOpened : Boolean = false,
        val selectedPaymentToDelete : PaymentCardModel? = null
    )

    sealed interface UiEvent{
        data object OnBackPressed: UiEvent
        data object OnAddCardClicked: UiEvent

        data object OnAddCardDismiss : UiEvent

        data object OnDeleteDialogDismiss : UiEvent
        data object OnDeleteCardConfirm : UiEvent

        data class OnDeleteCard(val card: PaymentCardModel) : UiEvent
        data class OnDefaultChanged(val card: PaymentCardModel) : UiEvent

        data class OnCardNumberChanged(val cardNumber: String) : UiEvent
        data class OnExpiryDateChanged(val expiryDate: String) : UiEvent
        data class OnCVVChanged(val cvv: String) : UiEvent
        data object OnSaveCardClicked : UiEvent
    }

    sealed interface UiEffect{
        data object OnNavigateBack : UiEffect
        data class ShowError(val message: String) : UiEffect
        data object CardSaved : UiEffect
    }

    data class PaymentCardState(
        val cardNumber: String = "",
        val cardType: String = "",
        val expiryDate: String = "",
        val cvv: String = "",
        val isDefault: Boolean = false,
        val isSaving: Boolean = false
    )
}