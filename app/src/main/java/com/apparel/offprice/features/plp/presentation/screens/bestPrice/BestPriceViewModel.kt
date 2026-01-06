package com.apparel.offprice.features.plp.presentation.screens.bestPrice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.common.utils.selectedCount
import com.apparel.offprice.features.plp.data.model.FilterType
import com.apparel.offprice.features.plp.data.model.filterList
import com.apparel.offprice.features.plp.data.model.inlineFilterList
import com.apparel.offprice.features.plp.data.model.sampleBestPriceProducts
import com.apparel.offprice.features.plp.data.model.samplePLPHorizontalListItems
import com.apparel.offprice.features.plp.data.model.sampleSortList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class BestPriceViewModel @Inject constructor(

) : ViewModel(), BestPriceContract {

    private val _state = MutableStateFlow(BestPriceContract.UiState())
    override val state: StateFlow<BestPriceContract.UiState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<BestPriceContract.UiEffect>()
    override val effect: SharedFlow<BestPriceContract.UiEffect> = _effectFlow.asSharedFlow()

    val brandSelectedCount: StateFlow<Int> =
        _state.map { uiState ->
            uiState.filters.selectedCount(FilterType.BRAND)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    val sizeSelectedCount: StateFlow<Int> =
        _state.map { uiState ->
            uiState.filters.selectedCount(FilterType.CLOTHING_SIZE)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    init {
        initialLoading()
    }


    override fun event(event: BestPriceContract.UiEvent) {
        when (event) {
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

            is BestPriceContract.UiEvent.FilterSheetOpen -> {
                _state.update {
                    it.copy(
                        isFilterSheetOpen = true,
                        selectedFilterType = event.filterType
                    )
                }
            }

            BestPriceContract.UiEvent.FilterSheetClosed -> {
                _state.update {
                    it.copy(
                        isFilterSheetOpen = false
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

            is BestPriceContract.UiEvent.WishlistClicked -> toggleWishlist(event.product.id)
            BestPriceContract.UiEvent.ScrollToTopClicked -> {
                viewModelScope.launch {
                    _effectFlow.emit(BestPriceContract.UiEffect.ScrollToTop)
                }
            }

            is BestPriceContract.UiEvent.OnSortSelected -> {
                _state.update { state ->
                    state.copy(
                        sortList = state.sortList.map {
                            it.copy(isSelected = it.title == event.sortItem.title)
                        }
                    )
                }
            }

            is BestPriceContract.UiEvent.OnFilterItemClicked -> {
                _state.update { state ->
                    state.copy(
                        filters = state.filters.map { group ->
                            if (group.type != event.filterType) return@map group

                            group.copy(
                                items = group.items.map { item ->
                                    when {
                                        item.id != event.itemId -> {
                                            if (!group.isMultiSelect) {
                                                item.copy(isSelected = false)
                                            } else item
                                        }

                                        else -> item.copy(isSelected = !item.isSelected)
                                    }
                                }
                            )
                        }
                    )
                }
            }

            is BestPriceContract.UiEvent.OnFilterTypeClicked -> {
                _state.update {
                    it.copy(
                        selectedFilterType = event.filterType
                    )
                }
            }

            BestPriceContract.UiEvent.OnClearAllClicked -> {
                _state.update { state ->
                    state.copy(
                        filters = state.filters.map { group ->
                            group.copy(
                                items = group.items.map { item ->
                                    if (item.isSelected) {
                                        item.copy(isSelected = false)
                                    } else item
                                }
                            )
                        }
                    )
                }
            }

            is BestPriceContract.UiEvent.OnInlineFilterItemClicked -> {
                _state.update { state ->
                    state.copy(
                        inlineFilters = state.inlineFilters.map { group ->
                            if (group.type != event.filterType) return@map group

                            group.copy(
                                items = group.items.map { item ->
                                    when {
                                        item.id != event.filterItem.id -> {
                                            if (!group.isMultiSelect) {
                                                item.copy(isSelected = false)
                                            } else item
                                        }

                                        else -> item.copy(isSelected = !item.isSelected)
                                    }
                                }
                            )
                        }
                    )
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

    fun initialLoading() {
        _state.value = _state.value.copy(
            lTwoCategoryList = samplePLPHorizontalListItems,
            productList = sampleBestPriceProducts,
            sortList = sampleSortList,
            filters = filterList,
            selectedFilterType = filterList.first().type,
            inlineFilters = inlineFilterList
        )
    }

}