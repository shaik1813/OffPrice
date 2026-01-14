package com.apparel.offprice.features.customerSupport.presentation.screens.privacyPolicy

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PrivacyPolicyViewModel @Inject constructor(

) : ViewModel(), PrivacyPolicyContract {


    private val _state = MutableStateFlow(PrivacyPolicyContract.UiState())
    override val state: StateFlow<PrivacyPolicyContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PrivacyPolicyContract.UiEffect>()
    override val effect: SharedFlow<PrivacyPolicyContract.UiEffect> = _effect.asSharedFlow()


    init {
        event(PrivacyPolicyContract.UiEvent.LoadPrivacyPolicy)
    }

    override fun event(event: PrivacyPolicyContract.UiEvent) {
        when (event) {
            PrivacyPolicyContract.UiEvent.LoadPrivacyPolicy -> {
                loadPrivacyPolicy()
            }
        }
    }

    private fun loadPrivacyPolicy() {
        val privacyPolicyText = """
<p>What Information Do We Collect?</p>

<p>
We collect information from you when you register on our site or place an order.
When ordering or registering on our site, as appropriate, you may be asked to enter
your name or e-mail address. You may, however, visit our site anonymously.
</p>

<p>What Do We Use Your Information For?</p>

<p>
Any of the information we collect from you may be used in one of the following ways:
</p>

<ul>
    <li>
        To personalize your experience (your information helps us to better respond to
        your individual needs)
    </li>
    <li>
        To improve our website (we continually strive to improve our website offerings
        based on the information and feedback we receive from you)
    </li>
    <li>
        To improve customer service (your information helps us to more effectively
        respond to your customer service requests and support needs)
    </li>
    <li>
        To process transactions
    </li>
    <li>
        Your information, whether public or private, will not be sold, exchanged,
        transferred, or given to any other company for any reason whatsoever, without
        your consent, other than for the express purpose of delivering the purchased
        product or service requested by the customer
    </li>
    <li>
        To send periodic emails. The email address you provide for order processing may
        be used to send you information and updates pertaining to your order, in addition
        to receiving occasional company news, updates, related product or service
        information, etc.
    </li>
</ul>

<p>
Note: At any time you would like to unsubscribe from receiving future emails, we include
detailed unsubscribe instructions at the bottom of each email.
</p>

<p>How Do We Protect Your Information?</p>

<p>
We implement a variety of security measures to maintain the safety of your personal
information when you access your personal information.
</p>

<hp>Do We Use Cookies?</p>

<p>
Yes. Cookies are small files that a site or its service provider transfers to your
computer’s hard drive through your web browser (if you

"""
        _state.value = _state.value.copy(
            privacyAndPolicyText = privacyPolicyText
        )
    }
}
