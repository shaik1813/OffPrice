package com.apparel.offprice.features.profile.presentation.screen.profileDetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.theme.lineColor
import com.apparel.offprice.common.theme.redColor
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.PastOrPresentSelectableDates
import com.apparel.offprice.common.utils.use
import com.apparel.offprice.features.profile.presentation.component.CategoryDropdown
import com.apparel.offprice.features.profile.presentation.component.CountryCodePicker
import com.apparel.offprice.features.profile.presentation.component.LabeledField
import com.apparel.offprice.features.profile.presentation.screen.myaccounts.ActionButtonsBar

/**
 * A composable function that displays the user's profile details.
 *
 * @param onNavigateToBack A lambda function to be invoked when the user navigates back.
 * @param onNavigateToPassword A lambda function to be invoked when the user wants to change their password.
 * @param viewModel The view model that provides the state and handles events for this screen.
 */

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailsScreen(
    onNavigateToBack: () -> Unit,
    onNavigateToPassword: () -> Unit,
    viewModel: ProfileDetailsViewModel = hiltViewModel()
) {

    val (state, event, effect) = use(viewModel = viewModel)

    effect.CollectInLaunchedEffect {
        when (it) {
            ProfileDetailsContract.UiEffect.OnNavigateToBack -> {
                onNavigateToBack()

            }

            ProfileDetailsContract.UiEffect.OnNavigateToChangePassword -> {
                onNavigateToPassword()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.label_personal).uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 4.dp)
                )
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = "Arrow back",
                    modifier = Modifier
                        .clickable {
                            event.invoke(ProfileDetailsContract.UiEvent.OnBackPressed)
                        }
                )
            },
            actions = {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                ) {
                    Text(
                        text = "English",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 14.sp
                        )
                    )
                    VerticalDivider(
                        modifier = Modifier
                            .height(16.dp)
                            .padding(horizontal = 4.dp)
                    )
                    Text(
                        text = "العربية",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 14.sp
                        )
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            ),
            windowInsets = WindowInsets(0, 0, 0, 0),
        )
        HorizontalDivider(thickness = 1.dp)
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.label_personal_details),
                    style = MaterialTheme.typography.titleMedium.copy(
                        letterSpacing = 0.1.sp
                    )
                )
                Text(
                    text = stringResource(R.string.label_edit_details),
                    modifier = Modifier.clickable {
                        event.invoke(
                            ProfileDetailsContract.UiEvent.EnabledEditing(
                                true
                            )
                        )
                    },
                    color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 12.sp)
                )
            }

            Card(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEFEFEF)
                )
            ) {
                Column(Modifier.padding(16.dp)) {

                    // ---------------- Name ----------------
                    LabeledField(
                        label = stringResource(R.string.name),
                        value = state.name,
                        enabled = state.isEditing,
                        onValueChange = {
                            event.invoke(
                                ProfileDetailsContract.UiEvent.OnNameChange(
                                    it
                                )
                            )
                        }
                    )
                    if (state.nameError != null) {
                        Text(
                            text = state.nameError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Spacer(Modifier.height(8.dp))

                    // ---------------- Email ----------------
                    LabeledField(
                        label = stringResource(R.string.email_address),
                        value = state.email,
                        enabled = state.isEditing,
                        onValueChange = {
                            event.invoke(
                                ProfileDetailsContract.UiEvent.OnEmailChange(
                                    it
                                )
                            )
                        }
                    )
                    if (state.emailError != null) {
                        Text(
                            text = state.emailError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Spacer(Modifier.height(8.dp))

                    // ---------------- Phone Number ----------------
                    Text(
                        text = stringResource(R.string.phone_no),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(6.dp))
                    OutlinedTextField(
                        value = state.phoneNumber,
                        onValueChange = {
                            if (state.isEditing) event.invoke(
                                ProfileDetailsContract.UiEvent.OnPhoneChange(
                                    it
                                )
                            )
                        },
                        textStyle = MaterialTheme.typography.bodySmall,
                        enabled = state.isEditing,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 0.75.dp,
                                color = lineColor,
                                shape = MaterialTheme.shapes.small
                            ),
                        leadingIcon = {
                            CountryCodePicker(
                                selected = state.phoneCode,
                                enabled = state.isEditing,
                                onSelect = {
                                    event.invoke(
                                        ProfileDetailsContract.UiEvent.SelectCountry(
                                            it
                                        )
                                    )
                                }
                            )
                        },
                        shape = MaterialTheme.shapes.small,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    if (state.phoneError != null) {
                        Text(
                            text = state.phoneError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Spacer(Modifier.height(8.dp))

                    // ---------------- DOB ----------------
                    Text(
                        text = stringResource(R.string.label_date_of_birth),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(6.dp))
                    OutlinedTextField(
                        value = state.dob,
                        onValueChange = {},
                        readOnly = true,
                        textStyle = MaterialTheme.typography.bodySmall,
                        enabled = state.isEditing,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 0.75.dp,
                                color = lineColor,
                                shape = MaterialTheme.shapes.small
                            ),
                        trailingIcon = {
                            if (state.isEditing) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_calendar),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clickable {
                                            event.invoke(ProfileDetailsContract.UiEvent.ToggleDatePicker)
                                        }
                                )
                            }
                        },
                        shape = MaterialTheme.shapes.small,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    if (state.dobError != null) {
                        Text(
                            text = state.dobError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Spacer(Modifier.height(8.dp))

                    // ---------------- Gender ----------------
                    CategoryDropdown(
                        label = stringResource(R.string.label_gender),
                        categoriesList = state.genderList,
                        selectedCategory = state.gender,
                        enabled = state.isEditing,
                        onCategorySelected = {
                            event.invoke(
                                ProfileDetailsContract.UiEvent.OnGenderChange(
                                    it
                                )
                            )
                        }
                    )
                    if (state.genderError != null) {
                        Text(
                            text = state.genderError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            if (!state.isEditing) {
                // ---------------- Password Section ----------------
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.password).uppercase(),
                        style = MaterialTheme.typography.titleMedium.copy(
                            letterSpacing = 0.1.sp
                        )
                    )
                    Text(
                        text = stringResource(R.string.label_change_password),
                        modifier = Modifier.clickable {
                            event.invoke(ProfileDetailsContract.UiEvent.ChangePasswordClick)
                        },
                        color = MaterialTheme.colorScheme.tertiary,
                        textDecoration = TextDecoration.Underline,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 12.sp)
                    )
                }
                Card(
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEFEFEF)
                    )
                ) {
                    Column(Modifier.padding(16.dp)) {

                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(R.string.password))
                                withStyle(style = SpanStyle(color = redColor)) {
                                    append("*")
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = state.password,
                            onValueChange = {},
                            textStyle = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 0.75.dp,
                                    color = lineColor,
                                    shape = MaterialTheme.shapes.small
                                ),
                            enabled = false,
                            shape = MaterialTheme.shapes.small,
                            singleLine = true,
                            visualTransformation = if (state.isPasswordVisible)
                                VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { event.invoke(ProfileDetailsContract.UiEvent.TogglePasswordVisibility) }) {
                                    Icon(
                                        painter = if (state.isPasswordVisible)
                                            painterResource(R.drawable.icon_password_visible) else painterResource(
                                            R.drawable.icon_password_hide
                                        ),
                                        contentDescription = null
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                errorBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,
                                cursorColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }

        }
        if (state.isEditing) {
            //-----------------------Bottom Space------------------------
            Spacer(modifier = Modifier.weight(1f))
            ActionButtonsBar(
                leftButtonText = stringResource(R.string.label_cancel).uppercase(),
                rightButtonText = stringResource(R.string.label_save).uppercase(),
                onLeftClick = {
                    event.invoke(ProfileDetailsContract.UiEvent.EnabledEditing(false))
                },
                onRightClick = {
                    event.invoke(ProfileDetailsContract.UiEvent.ToggleEdit)
                }
            )
        }
        if (state.showDatePicker) {
            //-----------------------Date Picker------------------------
            val datePickerState = rememberDatePickerState(
                selectableDates = PastOrPresentSelectableDates
            )
            DatePickerDialog(
                onDismissRequest = { event.invoke(ProfileDetailsContract.UiEvent.ToggleDatePicker) },
                confirmButton = {
                    TextButton(onClick = {
                        event.invoke(ProfileDetailsContract.UiEvent.OnDateSelected(datePickerState.selectedDateMillis))
                    }) {
                        Text(
                            text = stringResource(R.string.label_ok),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        event.invoke(ProfileDetailsContract.UiEvent.ToggleDatePicker)
                    }) {
                        Text(
                            text = stringResource(R.string.label_cancel).uppercase(),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                        selectedDayContentColor = Color.White,
                        todayDateBorderColor = MaterialTheme.colorScheme.primary,
                        todayContentColor = MaterialTheme.colorScheme.primary,
                        selectedYearContainerColor = MaterialTheme.colorScheme.primary,
                        selectedYearContentColor = Color.White
                    )
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ProfileDetailsScreenPreview() {
    ProfileDetailsScreen(
        onNavigateToBack = {},
        onNavigateToPassword = {},
    )
}
