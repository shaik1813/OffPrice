package com.apparel.offprice.features.customerSupport.presentation.screens.aboutUs

import androidx.lifecycle.ViewModel
import com.apparel.offprice.features.customerSupport.data.sampleBrandList
import com.apparel.offprice.features.customerSupport.data.sampleFeatureList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor(

) : ViewModel(), AboutUsContract {

    private val _state = MutableStateFlow(AboutUsContract.UiState())
    override val state: StateFlow<AboutUsContract.UiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AboutUsContract.UiEffect>()
    override val effect: SharedFlow<AboutUsContract.UiEffect> = _effect.asSharedFlow()

    init {
        loadBrands()
    }

    override fun event(event: AboutUsContract.UiEvent) {
        when (event) {
            else -> {}
        }
    }

    private fun loadBrands() {
        val aboutUsDescriptionText = """
        <p>We are an elevated fashion and lifestyle destination committed to  styling GCC’s GEN NOW to WOW. 
        We deliver on-trend shoes, bags, outfits,  beauty essentials and accessories right at your doorstep in the UAE,  KSA, Kuwait, Oman, Qatar & Bahrain. 
        We believe in looking like a  million bucks, not spending it – which is why we bring to you over 1200+ global brands at the most accessible prices.</p><br>
        <p>Off to the gym? Performance sportswear from <u>Nike</u>,<u> Adidas</u>,<u> Skechers</u> and On will power up your routine.</p><br>
        <p>Date night tonight? Get elegant outfits from <u>Tommy Hilfiger</u>,<u> Calvin Klein</u> or <u>Trendyol</u>. Complete your look with shoes & accessories from Charles & Keith, Dune London and Aldo and add finishing touches from our exclusive selection of fragrances designed by top influencers.</p><br>
        <p>Looking for an immersive shopping experience? Come visit GCC’s first phygital store at Dubai Hills Mall. An  innovative tech-led space that combines the best of both online &  offline shopping with online browsing & smart fitting rooms.</p><br>
        <p>At 6thStreet, we encourage you to live your best life and we think looking your best is a great place to start.</p>
        """.trimIndent()

        _state.update {
            it.copy(
                brands = sampleBrandList,
                aboutUsText = aboutUsDescriptionText,
                featureCards = sampleFeatureList
            )
        }
    }
}
