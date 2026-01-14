package com.apparel.offprice.features.customerSupport.presentation.screens.returnPolicy

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
class ReturnPolicyViewModel @Inject constructor(

) : ViewModel(), ReturnPolicyContract {


    private val _state = MutableStateFlow(ReturnPolicyContract.UiState())
    override val state: StateFlow<ReturnPolicyContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ReturnPolicyContract.UiEffect>()
    override val effect: SharedFlow<ReturnPolicyContract.UiEffect> = _effect.asSharedFlow()

    init {
        event(ReturnPolicyContract.UiEvent.LoadReturnPolicy)
    }

    override fun event(event: ReturnPolicyContract.UiEvent) {
        when (event) {
            ReturnPolicyContract.UiEvent.LoadReturnPolicy -> {
                loadReturnPolicy()
            }
        }
    }

    private fun loadReturnPolicy() {
        val returnPolicyText = """
<h3>1. What is your policy on returns?</h3>

<p>We understand that you may occasionally wish to return your purchases. We'll be happy to help you with the process.</p>

<p>We do not accept:</p>

<ul">
    <li>Returns can be initiated online only. We do not support returns in stores.</li>
    <li>Products damaged by misuse or showing signs of wear, even if they are still under warranty.</li>
    <li>Products that have been used, altered, assembled or installed, unless they are defective.</li>
    <li>Lingerie, inner wear or jewellery.</li>
    <li>Toiletries and perfumes.</li>
    <li>Bedding, bath products, kids' products, pillows, sheets, comforters, bed-in-a-bag, duvets, duvet covers, memory foam, towels and bathrobes that have been removed from their packaging.</li>
    <li>Products you wish to exchange for a different color or size.</li>
</ul>

<p>Returns can be initiated online only. We do not support returns in stores.</p>

<p>Please return your products in their original packaging, within <strong>14 days</strong> of delivery. Double-check that you've included the original invoice, all the accessories, promotional gifts and warranty cards that came with your order.</p>

<hr/>

<h3>2. Can I return part of a promotional purchase, where the discount was calculated on the total cost of my purchase?</h3>

<p>Yes, you can return items that were originally part of a promotion, but you will lose out on the discount.</p>

<hr/>

<h3>3. What if I want to return a product that was a part of Buy 1 and Get 1 Free offer or a Bundle Promo?</h3>

<p>If you want to return part of such an order, call Customer Service or return the product to our store.</p>

<p>Please note, you will only be able to place an online return request if you're returning the entire order.</p>

<p>Once we receive all the products we will issue the refund to your credit card, bank account, or Tabby account as the case may be.</p>

<p><strong>If you paid by cash, here's how your refund works:</strong></p>

<ul style="padding-left: 24px; margin-left: 8px;">
    <li>Store credit restricted to online purchases only; not applicable in physical stores.</li>
    <li>If you return your order by calling Customer Support, you can ask for a bank transfer or a refund wallet as store credit.</li>
    <li>If you return to store, you will receive your refund as a Store Credit or a refund to your bank account upon submission of your bank account details.</li>
    <li>If you place an online return request, you will receive your refund as a Store Credit or a refund to your bank account upon submission of your bank account details.</li>
</ul>

<hr/>

<h3>4. What if I want to return part of an order that came with a voucher?</h3>

<p>Whether you return your order partially or fully, your voucher will be cancelled. You will however, receive a refund for your return.</p>

<p>If you've already spent your voucher, the value of the voucher will be deducted from your refund.</p>

<hr/>

<h3>5. My original order was AED 100 or more, so I qualified for free shipping. Do I still get free shipping if I return all or part of my order?</h3>

<p>Yes, you will. We will not deduct shipping charges from your refund.</p>

<hr/>

<h3>6. Can I exchange my purchase for another product?</h3>

<p>We do not support exchanges currently. You'll be able to only return your products online, and use your online refund or Online Store Credit to buy a new product online.</p>

<hr/>

<h3>7. If I pay for my order with a Credit or Debit Card, how long will it take to process my refund?</h3>

<p>If you've paid by Credit or Debit Card, here's how long it will take:</p>

<ul style="padding-left: 24px; margin-left: 8px;">
    <li>2–3 business days to receive your returned items.</li>
    <li>2 business days to perform a quality check and issue the refund.</li>
    <li>Your bank or card issuer may take an additional 4–15 business days to process the transaction.</li>
</ul>

<hr/>

<h3>8. I paid for my order by Cash on Delivery. Can I return the products and get a cash refund?</h3>

<p>Your Cash on Delivery charge will only be refunded if you return your entire order.</p>

<p><strong>Here's how your refund works:</strong></p>

<ul style="padding-left: 24px; margin-left: 8px;">
    <li>Call for a pickup – You can ask Customer Support to transfer your refund to your bank account or opt for a refund to your wallet as store credit.</li>
    <li>Online return – We’re sorry, we won’t be able to offer a cash refund. You can opt for a bank transfer or wallet store credit.</li>
    <li>Store credit in your wallet can only be used online.</li>
</ul>

<hr/>

<h3>9. I paid for an order using only store credits, and then cancelled the order. Can I use the store credit again?</h3>

<p>Your store credit will be refunded back to your wallet and can be used again.</p>

<hr/>

<h3>10. I paid for part of my order with Store Credit and requested to pay the balance using Cash on Delivery. I cancelled the order before it arrived. How will my refund be processed?</h3>

<p>You’ll receive a refund for the same amount to your wallet as store credit. You can then use your store credit balance to shop online.</p>

<hr/>

<h3>11. I paid for an order using a Store Credit and a Credit or Debit Card, but I want to return a part of my order. How is my refund processed?</h3>

<p>If you want to return a part of your order, we will issue a refund to your wallet as store credit. The rest of the amount will be refunded to your Credit or Debit Card.</p>

<hr/>

<h3>12. I paid for part of my order using either Store Credit or a credit voucher and the rest with a Credit Card, Debit Card, PayPal or Tabby. I want to return the entire order now. How is my refund processed?</h3>

<p>After we verify your returns, we'll credit back the balance you spent through store credit wallet.</p>

<p>If you're a guest user and used a Credit Voucher, we will issue a new voucher for the same amount, valid for <strong>12 months</strong> and usable online only.</p>

<hr/>

<h3>13. I’ve just returned my Cash on Delivery order. Will my Cash on Delivery charge be refunded?</h3>

<p>If you return the entire order, your Cash on Delivery charges will be refunded. If you return only part of your order, they will not be refunded.</p>

<hr/>

<h3>14. If I returned the product to store, can I receive my refund partly in cash, and the remaining as store credit?</h3>

<p>No, refunds cannot be split. If you've paid by cash, you may opt for store credit or bank transfer.</p>

<p>Contact customer support at <strong>care@Offprice.com</strong>.</p>

<hr/>

<h3>15. If I decide I don't want my order when it's being delivered, can I return it directly to your delivery partner?</h3>

<p>Yes, you can return your order at the time of delivery.</p>

<hr/>

<h3>16. Will my VAT be refunded when my products are returned?</h3>

<p>Yes, your VAT will be refunded provided the invoice is included.</p>

"""

        _state.value = _state.value.copy(
            returnPolicyText = returnPolicyText
        )
    }
}