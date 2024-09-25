package googl.sarafan.testbankcard.features.history.presentation.models

data class HistoryStateUi(
    val historyValue: List<CardHistoryUi> = listOf(),
    val isLoading: Boolean = false
)