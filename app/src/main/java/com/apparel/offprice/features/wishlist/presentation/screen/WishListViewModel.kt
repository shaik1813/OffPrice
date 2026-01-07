package com.apparel.offprice.features.wishlist.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.pdp.data.model.clothSize
import com.apparel.offprice.features.plp.data.model.sampleProducts
import com.apparel.offprice.features.wishlist.presentation.screen.WishListContract.UiEffect.OnBackPressed
import com.apparel.offprice.features.wishlist.presentation.screen.WishListContract.UiEffect.OnNavigateToCart
import com.apparel.offprice.features.wishlist.presentation.screen.WishListContract.UiEffect.OnNavigateToPDP
import com.apparel.offprice.features.wishlist.presentation.screen.WishListContract.UiEffect.OnStartShopping
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

@HiltViewModel
class WishListViewModel @Inject constructor(

) : ViewModel(), WishListContract {


    private val _state = MutableStateFlow(WishListContract.UiState())
    override val state: StateFlow<WishListContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<WishListContract.UiEffect>()
    override val effect: SharedFlow<WishListContract.UiEffect> = _effectFlow.asSharedFlow()

    init {
        loadSampleData()
    }


    override fun event(event: WishListContract.UiEvent) {
        when (event) {
            is WishListContract.UiEvent.OnAddToBagClicked -> {
                //save the item to cart Logic
                _state.update { it.copy(isAddToBagDialog = true) }
            }

            WishListContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effectFlow.emit(OnBackPressed)
                }
            }

            WishListContract.UiEvent.OnStartShopping -> {
                viewModelScope.launch {
                    _state.update { it.copy(successfullyAddedToBag = false) }
                    _effectFlow.emit(OnStartShopping)
                }
            }

            is WishListContract.UiEvent.OnNavigateToPDP -> {
                viewModelScope.launch {
                    _effectFlow.emit(OnNavigateToPDP(event.product))
                }
            }

            is WishListContract.UiEvent.RemoveWishList -> {
                //Remove the Item from WishList
                _state.update { state ->
                    state.copy(
                        isWishListRemovalDialog = false,
                        selectedRemovedProductId = "",
                        wishListItems = sampleProducts.filter { it.id != event.productId },
                        wishListCount = sampleProducts.filter { it.id != event.productId }.size
                    )
                }
            }

            WishListContract.UiEvent.OnNavigateToCart -> {
                viewModelScope.launch {
                    _state.update { it.copy(successfullyAddedToBag = false) }
                    _effectFlow.emit(OnNavigateToCart)
                }
            }

            is WishListContract.UiEvent.OpenRemoveWishListDialog -> {
                _state.update {
                    it.copy(
                        isWishListRemovalDialog = true,
                        selectedRemovedProductId = event.productId
                    )
                }
            }

            WishListContract.UiEvent.OnDismissDialog -> {
                _state.update {
                    it.copy(
                        isWishListRemovalDialog = false,
                        selectedRemovedProductId = "",
                        isAddToBagDialog = false
                    )
                }
            }

            WishListContract.UiEvent.DecreaseQty -> {
                _state.update {
                    it.copy(quantity = if (it.quantity > 1) it.quantity - 1 else 1)
                }
            }
            WishListContract.UiEvent.IncreaseQty -> {
                _state.update {
                    it.copy(quantity = it.quantity + 1)
                }
            }
            WishListContract.UiEvent.MoveToBag -> {
                _state.update {
                    it.copy(
                        isAddToBagDialog = false,
                        successfullyAddedToBag = true
                    )
                }
            }
            is WishListContract.UiEvent.SelectSize -> {
                _state.update {
                    it.copy(selectedSizeId = event.sizeId)
                }
            }
        }
    }

    fun loadSampleData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    wishListCount = sampleProducts.size,
                    wishListItems = sampleProducts,
                    sizeListItem = clothSize
                )
            }
        }
    }
}