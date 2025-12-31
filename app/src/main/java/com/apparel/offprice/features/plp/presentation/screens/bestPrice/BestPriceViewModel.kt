package com.apparel.offprice.features.plp.presentation.screens.bestPrice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.plp.data.model.samplePLPHorizontalListItems
import com.apparel.offprice.features.plp.presentation.screens.ProductCardItems
import com.apparel.offprice.features.plp.presentation.screens.sampleProducts
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class BestPriceViewModel @Inject constructor(

): ViewModel(), BestPriceContract {

    private val _state = MutableStateFlow(BestPriceContract.UiState())
    override val state: StateFlow<BestPriceContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<BestPriceContract.UiEffect>()
    override val effect: SharedFlow<BestPriceContract.UiEffect> = _effectFlow.asSharedFlow()

    init {
        initialLoading()
    }


    override fun event(event: BestPriceContract.UiEvent) {
        when(event){
            BestPriceContract.UiEvent.NavigateToSearch -> {
                viewModelScope.launch {
                    _effectFlow.emit(BestPriceContract.UiEffect.NavigateToSearch)
                }
            }
            BestPriceContract.UiEvent.NavigateToWishlist -> {
                viewModelScope.launch {
                    _effectFlow.emit(BestPriceContract.UiEffect.NavigateToWishlist)
                }
            }
            is BestPriceContract.UiEvent.CategorySelected -> {
                _state.value = _state.value.copy(
                    selectedCategoryId = event.category.id
                )
            }

            BestPriceContract.UiEvent.ToggleFilterSheet -> {
                _state.update {
                    it.copy(
                        isFilterSheetOpen = !it.isFilterSheetOpen
                    )
                }
            }
            BestPriceContract.UiEvent.ToggleSortSheet -> {
                _state.update {
                    it.copy(
                        isSortSheetOpen = !it.isSortSheetOpen
                    )
                }
            }

            is BestPriceContract.UiEvent.WishlistClicked ->  toggleWishlist(event.product.id)
            BestPriceContract.UiEvent.ScrollToTopClicked -> {
                viewModelScope.launch {
                    _effectFlow.emit(BestPriceContract.UiEffect.ScrollToTop)
                }
            }
        }
    }

    fun onGridScroll(firstVisibleItemIndex: Int) {
        _state.update {
            it.copy(showScrollToTop = firstVisibleItemIndex > 1)
        }
    }

    private fun toggleWishlist(productId: String) {
        _state.update { currentState ->
            currentState.copy(
                productList = currentState.productList.map { product ->
                    if (product.id == productId) {
                        product.copy(isWishlist = !product.isWishlist)
                    } else product
                }
            )
        }
    }

    fun initialLoading(){
        _state.value = _state.value.copy(
            lTwoCategoryList = samplePLPHorizontalListItems,
            productList = sampleProducts
        )
    }

}