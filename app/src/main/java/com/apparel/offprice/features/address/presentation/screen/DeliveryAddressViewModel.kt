package com.apparel.offprice.features.address.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apparel.offprice.features.address.data.model.DeliveryAddressFilter
import com.apparel.offprice.features.address.data.model.DeliveryAddressModel
import com.apparel.offprice.features.home.data.model.countryList
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
class DeliveryAddressViewModel @Inject constructor(

) : ViewModel(), DeliveryAddressContract {

    private val _state = MutableStateFlow(DeliveryAddressContract.UiState())
    override val state: StateFlow<DeliveryAddressContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DeliveryAddressContract.UiEffect>()
    override val effect: SharedFlow<DeliveryAddressContract.UiEffect> = _effect.asSharedFlow()

    override fun event(event: DeliveryAddressContract.UiEvent) {
        when (event) {
            DeliveryAddressContract.UiEvent.OnBack -> {

            }

            is DeliveryAddressContract.UiEvent.OnDeleteAddress -> {
                _state.update {
                    it.copy(
                        isDeleteDialogOpened = true,
                        selectedAddressToDelete = event.address
                    )
                }
            }

            is DeliveryAddressContract.UiEvent.OnEditAddress -> {
                editAddress(event.address)
            }

            DeliveryAddressContract.UiEvent.OnAddAddressClicked -> {
                _state.update {
                    it.copy(isAddAddressOpened = true)
                }
            }

            DeliveryAddressContract.UiEvent.OnAddAddressDismiss -> {
                _state.update {
                    it.copy(isAddAddressOpened = false)
                }
            }

            DeliveryAddressContract.UiEvent.OnDeleteAddressConfirm -> {
                deleteAddress()
            }

            DeliveryAddressContract.UiEvent.OnDeleteDialogDismiss -> {
                _state.update {
                    it.copy(
                        isDeleteDialogOpened = false,
                        selectedAddressToDelete = null
                    )
                }
            }

            is DeliveryAddressContract.UiEvent.OnAddressChanged -> updateForm { copy(address = event.value) }
            is DeliveryAddressContract.UiEvent.OnAreaChanged -> updateForm { copy(area = event.value) }
            is DeliveryAddressContract.UiEvent.OnCityChanged -> updateForm { copy(city = event.value) }
            is DeliveryAddressContract.UiEvent.OnDefaultChanged -> updateForm { copy(isDefault = event.value) }
            is DeliveryAddressContract.UiEvent.OnNameChanged -> updateForm { copy(name = event.value) }
            is DeliveryAddressContract.UiEvent.OnPhoneChanged -> updateForm { copy(phoneNumber = event.value) }
            DeliveryAddressContract.UiEvent.OnSaveAddressClicked -> saveAddress()
            is DeliveryAddressContract.UiEvent.OnTagChanged -> updateForm { copy(tag = event.tag) }
            is DeliveryAddressContract.UiEvent.SelectCountry -> updateForm { copy(phoneCode = event.country) }
        }
    }

    private fun editAddress(address: DeliveryAddressModel) {
        _state.update {
            it.copy(
                isAddAddressOpened = true,
                addressForm = DeliveryAddressContract.AddressFormState(
                    name = address.userName,
                    phoneNumber = address.phone,
                    phoneCode = address.countryCode,
                    address = address.address,
                    city = address.city,
                    area = address.area,
                    isDefault = address.isDefault,
                    tag = address.tag
                )
            )
        }
    }

    private fun saveAddress() {
        val form = state.value.addressForm
        viewModelScope.launch {
            try {
                val newAddress = DeliveryAddressModel(
                    id = Random.nextInt(),
                    tag = form.tag,
                    userName = form.name,
                    address = form.address,
                    phone = form.phoneNumber,
                    countryCode = form.phoneCode,
                    isDefault = form.isDefault,
                    city = form.city,
                    area = form.area
                )

                _state.update {
                    it.copy(
                        isAddAddressOpened = false,
                        deliveryAddress = it.deliveryAddress + newAddress,
                        addressForm = DeliveryAddressContract.AddressFormState()
                    )
                }

                _effect.emit(DeliveryAddressContract.UiEffect.AddressSaved)

            } catch (e: Exception) {
                _effect.emit(
                    DeliveryAddressContract.UiEffect.ShowError("Failed to save address")
                )
            }
        }
    }

    private fun deleteAddress() {
        val address = state.value.selectedAddressToDelete ?: return
        _state.update { state ->
            state.copy(
                deliveryAddress = state.deliveryAddress.filterNot { it.id == address.id },
                isDeleteDialogOpened = false,
                selectedAddressToDelete = null
            )
        }
    }

    private fun updateForm(block: DeliveryAddressContract.AddressFormState.() -> DeliveryAddressContract.AddressFormState) {
        _state.update {
            it.copy(addressForm = it.addressForm.block())
        }
    }

    init {
        loadInitialData()
    }

    private fun loadInitialData() {

        val addressList = listOf(
            DeliveryAddressModel(
                id = 1,
                tag = DeliveryAddressFilter.HOME,
                userName = "Michael Jonathon",
                address = "Flat 402, Al Zahra Building Al Nahda Street, Al Nahda 2, Dubai, UAE",
                phone = "+971 436842594",
                isDefault = true,
                city = "UAE",
                area = "Dubai",
                countryCode = countryList.first()
            ),
            DeliveryAddressModel(
                id = 2,
                tag = DeliveryAddressFilter.WORK,
                userName = "John Rector",
                address = "Flat 402, Al Zahra Building Al Nahda Street, Al Nahda 2, Dubai, UAE",
                phone = "+971 436843678",
                isDefault = false,
                city = "UAE",
                area = "Dubai",
                countryCode = countryList.first()
            )
        )

        _state.update {
            it.copy(
                deliveryAddress = addressList
            )
        }

    }


}