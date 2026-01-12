package com.apparel.offprice.features.authentication.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.authentication.data.AuthPage


@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit,
    isVisible: Boolean,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            is LoginContract.UiEffect.Navigate -> {

            }

            is LoginContract.UiEffect.OnNavigateBack -> {
                onNavigateBack()
            }

            LoginContract.UiEffect.NavigateToHomeScreen -> {
                onNavigateBack()
            }
        }
    }

    BackHandler(
        enabled = state.currentPage != AuthPage.LOGIN
    ) {
        event(LoginContract.UiEvent.OnBackClick)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = if (state.playEnterAnimation) slideInVertically { it } + fadeIn()
            else EnterTransition.None
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush =
                            Brush.horizontalGradient(
                                listOf(
                                    Color(0xF13B292A),
                                    Color(0XFF9F1D26)
                                ),
                            )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login_bg), // your image
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(0.83f)
                        .fillMaxHeight(0.45f)
                        .align(Alignment.TopCenter)

                )

                Image(
                    painter = painterResource(R.drawable.close_login),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(22.dp)
                        .height(22.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            event(LoginContract.UiEvent.OnCloseLogin)
                        })

                /*Content section*/
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(modifier = Modifier.fillMaxHeight(0.25f)) {}

                    if (state.isResetPasswordScreen) {
                        ResetPasswordScreen(onDismiss = {
                            event(LoginContract.UiEvent.OnOpenForgot)
                        })
                    } else if (state.isForgotScreen)
                        ForgotPasswordScreen(
                            onClickContinue = { event(LoginContract.UiEvent.OnOpenResetPassword) },
                            onClickLogin = {
                                event(LoginContract.UiEvent.OnOpenLogin)
                            })
                    else {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.75f)
                                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                                .background(Color.White),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 26.dp)
                        ) {
                            item {
                                if (state.isLoginScreen)
                                    LoginForm(
                                        state,
                                        event,
                                        onForgotClick = {
                                            event(LoginContract.UiEvent.OnOpenForgot)
                                        })
                                else if (state.isSignUpScreen)
                                    SignUpForm(onLoginClick = {
                                        event(LoginContract.UiEvent.OnOpenLogin)
                                    })

                            }
                        }
                    }

                }

            }
        }
    }

}
