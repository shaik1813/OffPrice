package com.apparel.offprice.features.cart.presentation.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.cart.data.cartProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
) : ViewModel(), CartContract {


    private val _state = MutableStateFlow(CartContract.UiState())
    override val state: StateFlow<CartContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<CartContract.UiEffect>()
    override val effect: SharedFlow<CartContract.UiEffect> = _effectFlow.asSharedFlow()

    init {
        setInitialCart()
    }

    override fun event(event: CartContract.UiEvent) {
        when (event) {

            CartContract.UiEvent.onOpenBottomSheetOffer -> {
                viewModelScope.launch {
                    updateState { it.copy(isCouponOfferSheet = true) }
                }
            }

            CartContract.UiEvent.onCloseBottomSheetOffer -> {
                viewModelScope.launch {
                    updateState { it.copy(isCouponOfferSheet = false) }
                }
            }

            is CartContract.UiEvent.onOpenDeleteCartConfirm -> {
                viewModelScope.launch {
                    updateState {
                        it.copy(isDeleteCartDialog = true, deleteItemId = event.itemId)
                    }
                }
            }

            CartContract.UiEvent.onCloseDeleteCartConfirm -> {
                viewModelScope.launch {
                    updateState { it.copy(isDeleteCartDialog = false) }
                }
            }

            CartContract.UiEvent.OnToggleCheckedClubPoint -> {
                viewModelScope.launch {
                    updateState { it.copy(isCheckedClub = !it.isCheckedClub) }
                }
            }

            CartContract.UiEvent.OnToggleCheckedStorePoint -> {
                viewModelScope.launch {
                    updateState { it.copy(isCheckedStore = !it.isCheckedStore) }
                }
            }

            is CartContract.UiEvent.OnOpenQuantitySheet -> {
                viewModelScope.launch {
                    updateState {
                        it.copy(
                            isQuantitySheet = true,
                            selectCartPos = event.selectedPos
                        )
                    }
                }
            }

            CartContract.UiEvent.OnCloseQuantitySheet -> {
                viewModelScope.launch {
                    updateState { it.copy(isQuantitySheet = false) }
                }
            }

            CartContract.UiEvent.OnApplyToggleClick -> {
                viewModelScope.launch {
                    updateState {
                        if (it.isApplied)
                            it.copy(isApplied = false)
                        else
                            it.copy(isApplied = true)
                    }
                }
            }

            is CartContract.UiEvent.OnApplyCoupon -> {
                viewModelScope.launch {
                    delay(600)
                    updateState {
                        if (it.couponCode == event.code && it.isApplied) {

                            emitMessage("Already applied")

                            it.copy(
                                couponCode = event.code,
                                isApplied = true
                            )

                        } else {
                            it.copy(
                                couponCode = event.code,
                                isApplied = true
                            )
                        }
                    }
                }
            }

            is CartContract.UiEvent.OnCouponChanged -> {
                viewModelScope.launch {
                    updateState { it.copy(couponCode = event.value) }
                }
            }

            CartContract.UiEvent.OnShipFeeClick -> {
                viewModelScope.launch {
                    updateState { it.copy(isOpenShipFee = !it.isOpenShipFee) }
                }
            }

            CartContract.UiEvent.OnOpenOfferDetailDialog -> {
                viewModelScope.launch {
                    updateState { it.copy(isOfferDialog = true) }
                }
            }

            CartContract.UiEvent.OnCloseOfferDetailDialog -> {
                viewModelScope.launch {
                    updateState { it.copy(isOfferDialog = false) }
                }
            }

            is CartContract.UiEvent.OnDeleteCartItem -> {
                viewModelScope.launch {
                    updateState {
                        val updatedList = it.cartItems.filterNot { item -> item.id == event.itemId }

                        it.copy(cartItems = updatedList, isCartEmpty = updatedList.isEmpty())
                    }
                }
            }

            is CartContract.UiEvent.OnQuantitySelected -> {
                viewModelScope.launch {
                    updateState {
                        it.copy(selectedQuantity = event.quantity)
                    }
                }
            }

            CartContract.UiEvent.OnSubmitQuantity -> {
                viewModelScope.launch {
                    updateState {
                       val filterlist = it.cartItems.map { item ->
                            if(item.id == it.selectCartPos){
                                item.copy(quantity = it.selectedQuantity)
                            }
                            else item
                        }
                        it.copy(cartItems = filterlist)
                    }
                }
            }

            is CartContract.UiEvent.onWishListClicked -> {
               viewModelScope.launch {
                   updateState {
                       val updateList = it.cartItems.map { item ->
                           if(item.id == event.productId){
                               item.copy(isWishlist = !item.isWishlist)
                           }else item
                       }
                       it.copy(cartItems = updateList)
                   }
               }
            }
        }
    }

    fun setInitialCart() {
        val item = cartProducts
        _state.update {
            it.copy(cartItems = item)
        }
    }

    private fun updateState(reducer: (CartContract.UiState) -> CartContract.UiState) {
        _state.value = reducer(_state.value)
    }

    private fun emitMessage(msg: String) {
        viewModelScope.launch {
            _effectFlow.emit(CartContract.UiEffect.ShowMessage(msg))
        }
    }
}