package com.apparel.offprice.features.customerSupport.presentation.screens.contactInquiry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apparel.offprice.R
import com.apparel.offprice.common.component.DefaultTopAppBarWithAction
import com.apparel.offprice.common.utils.CollectInLaunchedEffect
import com.apparel.offprice.common.utils.use

@Composable
fun ContactInquiryScreen(
    onNavigateToBack: () -> Unit,
    onSearchClicked: () -> Unit,
    onWishlistClicked: () -> Unit,
    viewModel: ContactInquiryViewModel = hiltViewModel()
) {
    val (state, event, effect) = use(viewModel)
    val snackBarHostState = remember { SnackbarHostState() }

    effect.CollectInLaunchedEffect {
        when (it) {
            is ContactInquiryContract.UiEffect.ShowError ->
                snackBarHostState.showSnackbar(it.message)
            is ContactInquiryContract.UiEffect.ShowSuccess ->
                snackBarHostState.showSnackbar(it.message)
            ContactInquiryContract.UiEffect.FormSubmittedSuccessfully ->
                snackBarHostState.showSnackbar("Form submitted successfully!")
        }
    }

    Scaffold(
        topBar = {
            DefaultTopAppBarWithAction(
                title = stringResource(R.string.label_contact_inquiry),
                onBackPressed = onNavigateToBack,
                onSearchClicked = onSearchClicked,
                onWishlistClicked = onWishlistClicked
            )
        },
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                FormField(
                    label = "Name *",
                    value = state.name,
                    onValueChange = { event(ContactInquiryContract.UiEvent.OnNameChanged(it)) }
                )
            }

            item {
                PhoneNumberField(
                    label = "Phone Number",
                    countryCode = state.phoneCountryCode,
                    phoneNumber = state.phoneNumber,
                    onCountryCodeChanged = {
                        event(ContactInquiryContract.UiEvent.OnCountryCodeChanged(it))
                    },
                    onPhoneChanged = {
                        event(ContactInquiryContract.UiEvent.OnPhoneNumberChanged(it))
                    }
                )
            }

            item {
                FormField(
                    label = "Email Address",
                    value = state.emailAddress,
                    onValueChange = { event(ContactInquiryContract.UiEvent.OnEmailChanged(it)) },
                    keyboardType = KeyboardType.Email,
                    isEmail = true
                )
            }

            item {
                GenderDropdown(
                    label = "Gender",
                    selectedGender = state.selectedGender,
                    genderOptions = state.genderOptions,
                    onGenderSelected = {
                        event(ContactInquiryContract.UiEvent.OnGenderSelected(it))
                    }
                )
            }

            item {
                MessageField(
                    label = "Subject *",
                    value = state.message,
                    onValueChange = { event(ContactInquiryContract.UiEvent.OnMessageChanged(it)) }
                )
            }

            item {
                SubmitButton(
                    isLoading = state.isSubmitting,
                    isEnabled = state.isFormValid,
                    onClick = { event(ContactInquiryContract.UiEvent.OnSubmitForm) }
                )
            }
        }
    }
}

@Composable
fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isEmail: Boolean = false
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFDDDDDD),
                unfocusedBorderColor = Color(0xFFE5E5E5),
                disabledBorderColor = Color(0xFFE5E5E5),
                errorBorderColor = Color(0xFFD32F2F),
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().height(48.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            shape = RoundedCornerShape(4.dp),
            singleLine = true,
        )
    }
}

@Composable
fun PhoneNumberField(
    label: String,
    countryCode: String,
    phoneNumber: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onCountryCodeChanged: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    onPhoneChanged: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CountryCodeDropdown(
                countryCode = countryCode,
                onCountryCodeChanged = onCountryCodeChanged,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = onPhoneChanged,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFDDDDDD),
                    unfocusedBorderColor = Color(0xFFE5E5E5),
                    disabledBorderColor = Color(0xFFE5E5E5),
                    errorBorderColor = Color(0xFFD32F2F),
                    cursorColor = Color.Black
                ),
                modifier = Modifier.weight(2f).height(48.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                shape = RoundedCornerShape(4.dp),
                singleLine = true
            )
        }
    }
}

@Composable
fun CountryCodeDropdown(
    countryCode: String,
    onCountryCodeChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val expanded = remember { mutableStateOf(false) }
    val countryCodes = listOf("+971", "+966", "+974", "+212", "+20")

    Column(modifier) {
        OutlinedTextField(
            value = countryCode,
            onValueChange = {},
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFDDDDDD),
                unfocusedBorderColor = Color(0xFFE5E5E5),
                disabledBorderColor = Color(0xFFE5E5E5),
                errorBorderColor = Color(0xFFD32F2F),
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().height(48.dp),
            trailingIcon = {
                Icon(Icons.Default.KeyboardArrowDown, null)
            },
            shape = RoundedCornerShape(4.dp),
            singleLine = true
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            countryCodes.forEach {
                DropdownMenuItem(
                    text = { Text(it, style = MaterialTheme.typography.labelMedium) },
                    onClick = {
                        onCountryCodeChanged(it)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun GenderDropdown(
    label: String,
    selectedGender: String,
    genderOptions: List<GenderOption>,
    onGenderSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = selectedGender,
            onValueChange = {},
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFDDDDDD),
                unfocusedBorderColor = Color(0xFFE5E5E5),
                disabledBorderColor = Color(0xFFE5E5E5),
                errorBorderColor = Color(0xFFD32F2F),
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().height(48.dp),
            trailingIcon = {
                Icon(Icons.Default.KeyboardArrowDown, null)
            },
            shape = RoundedCornerShape(4.dp),
            singleLine = true
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            genderOptions.forEach {
                DropdownMenuItem(
                    text = { Text(it.label, style = MaterialTheme.typography.labelMedium) },
                    onClick = {
                        onGenderSelected(it.label)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun MessageField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFDDDDDD),
                unfocusedBorderColor = Color(0xFFE5E5E5),
                disabledBorderColor = Color(0xFFE5E5E5),
                errorBorderColor = Color(0xFFD32F2F),
                cursorColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().height(150.dp),
            shape = RoundedCornerShape(4.dp),
            maxLines = 5
        )
    }
}

@Composable
fun SubmitButton(
    isLoading: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = true,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(18.dp),
                strokeWidth = 2.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(
            text = if (isLoading) "SENDING..." else "SEND MESSAGE",
            style = MaterialTheme.typography.labelMedium
        )
    }

}
