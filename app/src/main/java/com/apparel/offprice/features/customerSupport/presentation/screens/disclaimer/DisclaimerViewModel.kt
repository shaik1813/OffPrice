package com.apparel.offprice.features.customerSupport.presentation.screens.disclaimer

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DisclaimerViewModel @Inject constructor(

) : ViewModel(), DisclaimerContract {

    private val _state = MutableStateFlow(DisclaimerContract.UiState())
    override val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DisclaimerContract.UiEffect>()
    override val effect = _effect.asSharedFlow()

    init {
        event(DisclaimerContract.UiEvent.LoadDisclaimer)
    }

    override fun event(event: DisclaimerContract.UiEvent) {
        when (event) {
            DisclaimerContract.UiEvent.LoadDisclaimer -> {
                loadDisclaimer()
            }
        }
    }

    private fun loadDisclaimer() {
        val disclaimerText = """
The Site is operated by Offprice.com and all rights thereto are owned and reserved by Offprice.com.

The Site made available and the software made available on this Site are provided to the User "as is" and without guarantees or representations of any kind (express or implied) or any liability to the fullest extent permissible pursuant to applicable law. Except in case of willful misconduct, Offprice.com shall not be liable for any damages that have been caused by or in connection with the use of the Site. In any case Offprice.com shall not be responsible for and disclaims any liability for any indirect, incidental, consequential and special damages, that have been caused by or in connection with the use of this Site.

Any product information or other information published via the Site is given to the best of Offprice.com's knowledge. All this information shall not, however, as far as legally permissible, create any guarantee or representation of any kind or any liability of Offprice.com and shall not relieve the User from undertaking its own investigations and tests.

Except as otherwise specifically provided in writing, Offprice.com's General Conditions of Sale shall not be altered by the Site, its content, materials and information. Without liability whatsoever Offprice.com may without notice modify and/or discontinue operation of all or portions of this Site at anytime in its sole discretion, and assumes no responsibility to update the Site.

Links to third-party sites ("Hyperlinks") do not constitute an endorsement of such third-party sites by Offprice.com and Offprice.com is not responsible for the availability of these sites or their contents. The hyper linking to these sites is at the User's own risk.

Any claims or suits associated with the Site or its use shall be governed and construed in accordance with the laws of UAE.

While Offprice.com shall endeavor to provide accurate product and pricing information, yet typographical errors might occur. Offprice.com cannot confirm the price of a product until after you order. In the event that a product is listed at an incorrect price or with incorrect information due to an error in pricing or product information, Offprice.com shall have the right, at our sole discretion, to refuse or cancel any orders placed for that product, unless the product has already been dispatched.

In the event that an item is mis-priced, Offprice.com may, at its discretion, either contact you for instructions or cancel your order and notify you of such cancellation. Unless the product ordered by you has been dispatched, your offer will not be deemed accepted and Offprice.com will have the right to modify the price of the product and contact you for further instructions using the e-mail address provided by you during the time of registration, or cancel the order and notify you of such cancellation.

In the event that Offprice.com accepts your order the same shall be debited to your credit card account and duly notified to you by email that the payment has been processed. The payment may be processed prior to Offprice.com dispatch of the product that you have ordered. If we have to cancel the order after we have processed the payment, the said amount will be reversed back to your credit card account.

Offprice.com reserves the right at all times to discontinue or modify the Terms of Use and/or our Privacy Policy as we deem necessary or desirable without any prior notification. Such changes may include, among other things, the adding of certain fees and charges. Any such modifications would be effective immediately.

We suggest that you read our Terms and Conditions and Privacy Policy from time to time to stay informed. Any use of the site after such modifications would be deemed to constitute acceptance of the modified terms by the End User.

Sign up & be the first to know about product arrivals & exclusive offers.
"""

        _state.value = _state.value.copy(
            disclaimerText = disclaimerText
        )
    }

}