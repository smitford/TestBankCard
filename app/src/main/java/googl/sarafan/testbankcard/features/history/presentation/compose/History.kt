package googl.sarafan.testbankcard.features.history.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import googl.sarafan.testbankcard.R
import googl.sarafan.testbankcard.features.history.presentation.HistoryViewModel
import googl.sarafan.testbankcard.features.history.presentation.models.CardHistoryUi
import googl.sarafan.testbankcard.features.history.presentation.models.HistoryEvent
import googl.sarafan.testbankcard.features.history.presentation.models.HistoryStateUi
import googl.sarafan.testbankcard.uikit.compose.DropDownTableItem
import googl.sarafan.testbankcard.uikit.compose.LoadingContainer
import googl.sarafan.testbankcard.uikit.getScreenWidth

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    val viewModel: HistoryViewModel = hiltViewModel()

    val state by viewModel.historyStateUi.collectAsState()

    LoadingContainer(
        modifier = modifier,
        isLoading = state.isLoading
    ) {
        HistoryScreen(
            state = state,
            onEvent = viewModel::executeEvent
        )
    }
}

@Composable
fun HistoryScreen(
    state: HistoryStateUi,
    onEvent: (event: HistoryEvent) -> Unit
) {
    val (historyValue) = state
    val descriptionTitles = listOf(
        stringResource(R.string.card_type),
        stringResource(R.string.bank_url),
        stringResource(R.string.bank_phone),
        stringResource(R.string.city),
        stringResource(R.string.latitude),
        stringResource(R.string.longitude)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier,

            ) {

            item {
                TableTitles()
            }

            items(historyValue) { item ->

                DropDownTableItem(
                    value = listOf(
                        item.cardNumber,
                        item.cardScheme,
                        item.bankName,
                        item.countryName
                    ),
                    description = listOf(
                        item.cardType,
                        item.url,
                        item.phone,
                        item.city,
                        item.latitude.toString(),
                        item.longitude.toString()
                    ),
                    descriptionTitle = descriptionTitles,
                )

            }
        }
        Button(
            onClick = { onEvent(HistoryEvent.ClearHistory) }
        ) {
            Text(
                text = stringResource(R.string.clear_history)
            )
        }
    }

}

@Composable
fun TableTitles() {
    val valueWidth = (getScreenWidth() - 16) / 4
    Spacer(modifier = Modifier.height(4.dp))
    Row {
        Spacer(modifier = Modifier.width(24.dp))
        TableTitle.entries.forEach { item ->
            Text(
                text = stringResource(item.title),
                modifier = Modifier.width(valueWidth.dp),
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    val historyList = listOf(
        CardHistoryUi(
            cardNumber = "1329434"
        ),
        CardHistoryUi(
            cardNumber = "1329434"
        ),
        CardHistoryUi(
            cardNumber = "1329434"
        )

    )
    HistoryScreen(
        state = HistoryStateUi(
            historyValue = historyList
        ),
        onEvent = {})
}