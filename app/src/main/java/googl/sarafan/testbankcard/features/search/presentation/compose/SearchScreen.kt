package googl.sarafan.testbankcard.features.search.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import googl.sarafan.testbankcard.R
import googl.sarafan.testbankcard.features.search.presentation.SearchViewModel
import googl.sarafan.testbankcard.features.search.presentation.models.Bank
import googl.sarafan.testbankcard.features.search.presentation.models.CardValue
import googl.sarafan.testbankcard.features.search.presentation.models.Country
import googl.sarafan.testbankcard.features.search.presentation.models.SearchEvent
import googl.sarafan.testbankcard.features.search.presentation.models.SearchUiState
import googl.sarafan.testbankcard.uikit.compose.CardNumberVisualTransformation
import googl.sarafan.testbankcard.uikit.compose.EditTextWithComments
import googl.sarafan.testbankcard.uikit.compose.LoadingContainer
import googl.sarafan.testbankcard.uikit.theme.TestBankCardTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val state by viewModel.searchUiState.collectAsState()

    LoadingContainer(modifier = modifier.fillMaxSize(), isLoading = state.isLoading) {
        SearchScreen(state = state, onEvent = viewModel::executeEvent)
    }

}

@Composable
fun SearchScreen(
    state: SearchUiState,
    onEvent: (event: SearchEvent) -> Unit
) {
    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                EditTextWithComments(
                    modifier = Modifier.weight(0.75f),
                    value = state.cardNumber,
                    label = stringResource(R.string.edit_text_label),
                    onValueChange = { newValue ->
                        val digitsOnly = newValue.filter { it.isDigit() }
                        if (digitsOnly.length <= 8)
                            onEvent(SearchEvent.OnCardNumberChanged(digitsOnly))
                    },
                    hint = stringResource(R.string.edit_text_card_number_hint),
                    visualTransformation = CardNumberVisualTransformation()
                )
                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    modifier = Modifier.weight(0.25f),
                    onClick = { onEvent(SearchEvent.Search) },
                    enabled = state.cardNumber.length >= 6
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            SearchValueContainer(
                cardValue = state.cardValue,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    TestBankCardTheme {
        SearchScreen(
            state = SearchUiState(
                cardNumber = "12345",
                cardValue = CardValue(
                    cardScheme = "visa",
                    cardType = "debit",
                    country = Country(
                        countryName = "Ukraine",
                        latitude = 49f,
                        longitude = 32f
                    ),
                    bank = Bank(
                        bankName = "BankName",
                        url = "bankUrl",
                        phone = "8 912 374 85 34",
                        city = "bankCity"
                    )
                ),
                isLoading = false
            ),
            onEvent = {}
        )
    }
}