package com.apparel.offprice.features.paymentCard.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.paymentCard.data.PaymentCardModel
import com.apparel.offprice.features.paymentCard.data.sampleCardList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PaymentCardViewModel @Inject constructor(

) : ViewModel(), PaymentCardContract {

    private val _state = MutableStateFlow(PaymentCardContract.UiState())
    override val state: StateFlow<PaymentCardContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PaymentCardContract.UiEffect>()
    override val effect: SharedFlow<PaymentCardContract.UiEffect> = _effect.asSharedFlow()


    override fun event(event: PaymentCardContract.UiEvent) {
        when (event) {
            PaymentCardContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effect.emit(PaymentCardContract.UiEffect.OnNavigateBack)
                }
            }

            PaymentCardContract.UiEvent.OnAddCardClicked -> {
                _state.update {
                    it.copy(isAddCardOpened = true)
                }
            }

            PaymentCardContract.UiEvent.OnDeleteCardConfirm -> {
                deleteCard()
            }

            PaymentCardContract.UiEvent.OnDeleteDialogDismiss -> {
                _state.update {
                    it.copy(
                        isDeleteDialogOpened = false,
                        selectedPaymentToDelete = null
                    )
                }
            }

            is PaymentCardContract.UiEvent.OnDeleteCard -> {
                _state.update {
                    it.copy(
                        isDeleteDialogOpened = true,
                        selectedPaymentToDelete = event.card
                    )
                }
            }

            is PaymentCardContract.UiEvent.OnDefaultChanged -> {
                _state.update { state ->
                    state.copy(
                        paymentCards = state.paymentCards.map { card ->
                            card.copy(isDefault = card.id == event.card.id)
                        }
                    )
                }
            }

            is PaymentCardContract.UiEvent.OnCVVChanged -> {
                updateForm { copy(cvv = event.cvv) }
            }

            is PaymentCardContract.UiEvent.OnCardNumberChanged -> {
                updateForm { copy(cardNumber = event.cardNumber) }
            }

            is PaymentCardContract.UiEvent.OnExpiryDateChanged -> {
                updateForm { copy(expiryDate = event.expiryDate) }
            }

            PaymentCardContract.UiEvent.OnSaveCardClicked -> {
                savePaymentCard()
            }

            PaymentCardContract.UiEvent.OnAddCardDismiss -> {
                _state.update {
                    it.copy(isAddCardOpened = false)
                }
            }
        }
    }

    init {
        initialData()
    }

    fun initialData() {
        _state.update {
            it.copy(
                paymentCards = sampleCardList
            )
        }
    }

    private fun updateForm(block: PaymentCardContract.PaymentCardState.() -> PaymentCardContract.PaymentCardState) {
        _state.update {
            it.copy(cardForm = it.cardForm.block())
        }
    }

    fun savePaymentCard() {
        val form = state.value.cardForm
        viewModelScope.launch {
            try {
                val newCard = PaymentCardModel(
                    id = Random.nextInt(),
                    cardNumber = form.cardNumber,
                    cardType = "Credit Card",
                    expiryDate = form.expiryDate,
                    cvv = form.cvv,
                    isDefault = form.isDefault
                )

                _state.update {
                    it.copy(
                        isAddCardOpened = false,
                        paymentCards = it.paymentCards + newCard,
                        cardForm = PaymentCardContract.PaymentCardState()
                    )
                }

                _effect.emit(PaymentCardContract.UiEffect.CardSaved)

            } catch (e: Exception) {
                _effect.emit(
                    PaymentCardContract.UiEffect.ShowError("Failed to save address")
                )
            }
        }
    }


    fun deleteCard() {
        val card = state.value.selectedPaymentToDelete ?: return
        _state.update { state ->
            state.copy(
                paymentCards = state.paymentCards.filterNot { it.id == card.id },
                isDeleteDialogOpened = false,
                selectedPaymentToDelete = null
            )
        }
    }
}