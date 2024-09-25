package googl.sarafan.testbankcard.features.search.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import googl.sarafan.testbankcard.R
import googl.sarafan.testbankcard.features.search.presentation.models.Bank
import googl.sarafan.testbankcard.features.search.presentation.models.CardValue
import googl.sarafan.testbankcard.features.search.presentation.models.Country
import googl.sarafan.testbankcard.features.search.presentation.models.SearchEvent
import googl.sarafan.testbankcard.uikit.compose.DualInfoRow
import googl.sarafan.testbankcard.uikit.compose.TextLabeledColumn
import googl.sarafan.testbankcard.uikit.compose.TextLabeledRow

@Composable
fun SearchValueContainer(
    cardValue: CardValue,
    modifier: Modifier = Modifier,
    onEvent: (event: SearchEvent) -> Unit
) {
    val (bankName, bankUrl, bankPhone, bankCity) = cardValue.bank
    val (countryName, countryLatitude, countryLongitude) = cardValue.country
    Column(
        modifier = modifier.scrollable(
            state = rememberScrollState(),
            orientation = Orientation.Vertical,
            enabled = true
        ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        TextLabeledRow(
            label = stringResource(R.string.card_scheme),
            value = cardValue.cardScheme
        )
        TextLabeledColumn(
            label = stringResource(R.string.country),
            value = countryName
        ) {
            DualInfoRow(
                firstLabel = stringResource(R.string.latitude),
                firstValue = countryLatitude.toString(),
                secondLabel = stringResource(R.string.longitude),
                secondValue = countryLongitude.toString(),
                onClick = {
                    onEvent.invoke(SearchEvent.OpenMap)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextLabeledColumn(
            label = stringResource(R.string.bank),
            value = bankName
        ) {
            Column {
                Text(text = bankCity, modifier = Modifier)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = bankPhone,
                    modifier = Modifier.clickable {
                        onEvent.invoke(SearchEvent.CallBank)
                    },
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = bankUrl,
                    modifier = Modifier.clickable {
                        onEvent.invoke(SearchEvent.OpenUrl)
                    },
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchValueContainerPreview() {
    SearchValueContainer(
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
        onEvent = {}
    )

}

