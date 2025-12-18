package com.apparel.offprice.features.address.presentation.screen

import com.apparel.offprice.common.utils.UnidirectionalViewModel
import com.apparel.offprice.features.address.data.model.DeliveryAddressFilter
import com.apparel.offprice.features.address.data.model.DeliveryAddressModel
import com.apparel.offprice.features.home.data.model.Country
import com.apparel.offprice.features.home.data.model.countryList

interface DeliveryAddressContract :
    UnidirectionalViewModel<DeliveryAddressContract.UiState, DeliveryAddressContract.UiEvent, DeliveryAddressContract.UiEffect> {

    data class UiState(
        val isLoading: Boolean = false,
        val deliveryAddress: List<DeliveryAddressModel> = emptyList(),
        val isAddAddressOpened : Boolean = false,
        val addressForm: AddressFormState = AddressFormState(),
        val deliveryAddressTag : DeliveryAddressFilter = DeliveryAddressFilter.HOME,
        val isDeleteDialogOpened : Boolean = false,
        val selectedAddressToDelete : DeliveryAddressModel? = null
    )

    sealed interface UiEvent {
        data object OnBack : UiEvent
        data class OnEditAddress(val address: DeliveryAddressModel) : UiEvent
        data class OnDeleteAddress(val address: DeliveryAddressModel) : UiEvent

        data object OnAddAddressClicked : UiEvent
        data object OnAddAddressDismiss : UiEvent

        data object OnDeleteDialogDismiss : UiEvent
        data object OnDeleteAddressConfirm : UiEvent

        data object OnSaveAddressClicked: UiEvent

        // Form input changes
        data class OnNameChanged(val value: String) : UiEvent
        data class OnPhoneChanged(val value: String) : UiEvent
        data class OnAddressChanged(val value: String) : UiEvent
        data class OnCityChanged(val value: String) : UiEvent
        data class OnAreaChanged(val value: String) : UiEvent
        data class OnDefaultChanged(val value: Boolean) : UiEvent
        data class OnTagChanged(val tag: DeliveryAddressFilter) : UiEvent

        data class SelectCountry(val country: Country) : UiEvent

    }

    sealed interface UiEffect {
        data object OnNavigateToBack : UiEffect

        data class ShowError(val message: String) : UiEffect

        data object AddressSaved : UiEffect

    }

    data class AddressFormState(
        val name: String = "",
        val phoneNumber: String = "",
        val phoneCode: Country = countryList.first(),
        val address: String = "",
        val city: String = "",
        val area: String = "",
        val isDefault: Boolean = false,
        val tag: DeliveryAddressFilter = DeliveryAddressFilter.HOME,
        val isSaving: Boolean = false
    )

}