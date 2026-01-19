package com.apparel.offprice.features.customerSupport.presentation.screens.termsAndCondition

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
class TermsAndConditionViewModel @Inject constructor(

) : ViewModel(), TermsAndConditionContract {


    private val _state = MutableStateFlow(TermsAndConditionContract.UiState())
    override val state: StateFlow<TermsAndConditionContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<TermsAndConditionContract.UiEffect>()
    override val effect: SharedFlow<TermsAndConditionContract.UiEffect> = _effect.asSharedFlow()

    init {
        event(TermsAndConditionContract.UiEvent.LoadTermsCondition)
    }

    override fun event(event: TermsAndConditionContract.UiEvent) {
        when (event) {
            TermsAndConditionContract.UiEvent.LoadTermsCondition -> {
                laodTermsCondition()
            }
        }
    }

    private fun laodTermsCondition() {
        val termsAndConditionText = """
<p>
Apparel Free Zone Co. LLC, a company incorporated under the laws of Dubai, United Arab Emirates
(referred to herein as "R &amp; B Fashion", "we", "our" or "us") operates the Internet e-commerce
website randbfashion.com (or any successor site) and its related sub-domains, sites, Services
and tools ("Website").
The Website is provided as a service to you and is intended to allow you and other users to
browse and order products and other goods or services ("Products") offered for sale on the
Website ("Services").
R &amp; B Fashion reserves the right to delete, modify, or supplement the content of the Website
at any time for any reason without prior notification, and will use reasonable efforts to include
up-to-date and accurate information on the Website.
NOTWITHSTANDING ANY ORAL OR WRITTEN INFORMATION, OPINIONS, EVALUATIONS, DESCRIPTIONS OR
CONSULTATIONS OF ANY KIND FURNISHED BY R &amp; B Fashion TO THE CONTRARY, YOU SPECIFICALLY
AGREE TO THE TERMS SET FORTH BELOW.
</p>

<h3>1.TERMS AND CONDITIONS:</h3>

<p>
1.These Terms of Use (the "Terms") are between R &amp; B Fashion and you as an end user of
R &amp; B Fashion’s Website.
Changes in law or changes in R &amp; B Fashion’s business may require changes to be made to
the Terms from time to time ("Revised Terms"), so R &amp; B Fashion encourages you to review
the Terms periodically for any such changes.
It is your responsibility to carefully read, agree with and accept the Terms and Website’s
Privacy Policy (the "Privacy Policy") on each occasion you use the Website, and your continued
use of the Website shall signify your acceptance to be bound by the latest Terms.
If you do not agree to (or cannot comply with) any of the Terms, do not use this Website.
</p>
<p>
2.If you opt to register with the Website, then at the time of registration with the Website, as a
Registered User, you must carefully read these Terms and accept them.
Each time a change is made to the Terms, you as a Registered User, will be prompted to accept
the Revised Terms before you can proceed with your next purchase of Products on the Website.
</p>

<h3>2.USER CONDUCT AND CONTENT</h3>

<p>
1.You agree to comply with all legal requirements of the applicable jurisdiction(s) with regard to
your use of the Website, and you acknowledge that you are entirely responsible for ensuring
your own familiarity with such requirements and your own compliance with the same.
</p>

<p>
2.The Website may at times provide opportunities for users to post reviews and other comments,
questions, suggestions or other information ("User Content") on or through this Website by
e-mail or telephone, or otherwise disclosed, submitted or offered in connection with your use
of this Website.
You warrant that any such User Content submitted by you to the Website is original (and does
not infringe the intellectual property rights of others), and you hereby grant R &amp; B Fashion a
perpetual, irrevocable, non-exclusive, royalty-free, transferable right and licence to use such
User Content however R &amp; B Fashion desires.
This includes without limitation the right to copy, modify, delete in its entirety, adapt,
publish, translate, create derivative works from and/or sell and/or distribute such User
Content and/or incorporate such User Content into any form, medium or technology throughout
the world, without any further recourse to you, and you hereby waive any moral rights in such
User Content.
You agree that you will not post any content that is contrary to morality, defamatory,
inflammatory, that infringes intellectual property rights, or that may otherwise breach
common decency or be contrary to law.
</p>
<p>
3.R &amp; B Fashion will be entitled to use, reproduce, disclose, modify, adapt, create derivative
works from, publish, display and distribute any User Content you submit for any purpose
whatsoever, without restriction and without compensating you in any way.
R &amp; B Fashion is and shall be under no obligation:
(1) to maintain any User Content in confidence;
(2) to pay to user any compensation for any User Content; or
(3) to respond to any User Content.
</p>

<p>
4.Our agree that any User Content submitted by you to the Website will not violate the Terms or
any right of any third party, including without limitation, copyright, trademark, privacy or
other personal or proprietary right/s, and will not cause injury to any person or entity.
You further agree that no User Content submitted by you to the Website will be or contain
libelous or otherwise unlawful, threatening, abusive or obscene material, or contain software
viruses, political campaigning, commercial solicitation, chain letters, mass mailings or any
form of "spam".
</p>


<p>
5.R &amp; B Fashion does not regularly review posted User Content but does reserve the right (but
not the obligation) to monitor and edit or remove any User Content submitted to the Website.
You grant R &amp; B Fashion the right to use the name that you submit in connection with any
User Content.
You agree not to use a false email address, impersonate any person or entity, or otherwise
mislead as to the origin of any User Content you submit.
You are and shall remain solely responsible for the content of any User Content you make
and you agree to indemnify R &amp; B Fashion and its affiliates for all claims resulting from any
User Content you submit.
R &amp; B Fashion and its affiliates take no responsibility and assume no liability for any User
Content submitted by you or any third party.
</p>

<h3>3.ACCOUNT AND REGISTERED USER:</h3>

<p>
1.In order to purchase any Products on the Website, you may either register and create an
account secured by a password (the "Account") or submit your order without creating the
Account i.e. as a "Guest".
Any Account so created is personal to you, and you may not transfer it to another person.
Your registration or order (in case of Guest) shall be complete after you accept the Terms.
The Terms will apply each time you submit an order for any Products on the Website.
</p>

<p>
2.Single Sign-On: Our Website offers a single-sign-on for worldwide participating websites and
online platforms of R &amp; B Fashion and its affiliated companies currently comprising of
6thstreet.com, apparelgroupglobal.com (jointly or severally referred as "Participating Website").
The number of Participating Website may also vary in the course of time.
After a one-time registration on one of the Participating Website, you can sign on (login) to
each of the Participating Website with uniform credentials, without having to go through a
separate registration and sign-on process each time ("Single Sign-On").
</p>

<p>
3.If you register and create an Account, you are a "Registered User".
To become a Registered User or to submit an order for any Products on the Website as a Guest,
you must be at least 21 years of age, and must provide true, accurate, current, and complete
in all respects information about yourself (including name, date of birth, email address,
credit card details, and other details) as requested during the Account creation process or
at the time of submission of the order on the Website as a Guest.
If you are under the age of 21 years, you may use this Website only under the supervision of
a parent or legal guardian who agrees to be bound by these Terms.
If you are a parent or legal guardian agreeing to these Terms on behalf of a person under
the age of 21 years, you are fully responsible for his or her use of this Website, including
all financial charges and legal liability that he or she may incur.
R &amp; B Fashion reserves the right to refuse service, terminate Accounts, or cancel orders
at their sole discretion.
</p>

<p>
4.By registering and using the Website, you warrant that the information provided by you in
the course of the registration process or in the course of submission of order for any
Products on the Website as a Guest is true, accurate, current, and complete in all respects
and you undertake to ensure that such information is kept up-to-date and agree that such
information shall be treated as per the Privacy Policy.
</p>

<p>
5.You are responsible for maintaining the confidentiality of your Account and password and
for restricting access to your personal computer or electronic or other mobile or wireless
device, and you agree to accept responsibility for all activities that occur under your
Account or password.
R &amp; B Fashion assumes no liability to any person for any loss or damage which may arise as
a result of any failure by you in protecting your password or Account.
If R &amp; B Fashion has reason to believe that there is likely to be a breach of security or
misuse of the Website, we may require you to change your password or we may suspend your
account without any liability to us.
</p>
"""
        _state.value = _state.value.copy(
            termsAndConditionText = termsAndConditionText
        )
    }
}