package googl.sarafan.testbankcard.features.history.presentation.models

data class HistoryState(
    val historyValue: List<CardHistoryUi> = listOf(),
    val isLoading: Boolean = false
)
