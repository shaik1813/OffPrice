package com.apparel.offprice.features.wishlist.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.home.presentation.screens.search.SearchContract
import com.apparel.offprice.features.plp.presentation.screens.ProductCardItems
import com.apparel.offprice.features.plp.presentation.screens.sampleProducts
import com.apparel.offprice.features.wishlist.presentation.screen.WishListContract.UiEffect.*
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
class WishListViewModel @Inject constructor(

): ViewModel(),WishListContract {


    private val _state = MutableStateFlow(WishListContract.UiState())
    override val state: StateFlow<WishListContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<WishListContract.UiEffect>()
    override val effect: SharedFlow<WishListContract.UiEffect> = _effectFlow.asSharedFlow()

    init {
        loadSampleData()
    }


    override fun event(event: WishListContract.UiEvent) {
        when(event){
            is WishListContract.UiEvent.OnAddToBagClicked -> {
                //save the item to cart Logic
                openSuccessfulWishListDialog()
            }
            WishListContract.UiEvent.OnBackPressed -> {
                viewModelScope.launch {
                    _effectFlow.emit(OnBackPressed)
                }
            }
            WishListContract.UiEvent.OnStartShopping -> {
                viewModelScope.launch {
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
                dismissDialog()
            }

            WishListContract.UiEvent.OnNavigateToCart -> {
                viewModelScope.launch {
                    _effectFlow.emit(OnNavigateToCart)
                }
            }
        }
    }

    fun loadSampleData(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(2000)
            _state.update { it.copy(wishListCount = sampleProducts.size , wishListItems = sampleProducts, isLoading = false) }
        }
    }

    fun openSuccessfulWishListDialog(){
        _state.update { it.copy(isAddToBagDialog = true) }
    }


    fun openRemoveWishListDialog(productCardItems: ProductCardItems){
        _state.update { it.copy(isWishListRemovalDialog = true) }
    }

    fun dismissDialog(){
        _state.update { it.copy(isWishListRemovalDialog = false , isAddToBagDialog = false) }
    }
}