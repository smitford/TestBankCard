package googl.sarafan.testbankcard.features.history.presentation.models

sealed class HistoryEvent {
    data object ClearHistory : HistoryEvent()
}