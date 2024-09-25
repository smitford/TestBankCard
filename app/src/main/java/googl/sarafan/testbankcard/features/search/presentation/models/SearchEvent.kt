package googl.sarafan.testbankcard.features.search.presentation.models

sealed class SearchEvent {
    data object Search : SearchEvent()
    data object OpenUrl : SearchEvent()
    data object CallBank : SearchEvent()
    data object OpenMap : SearchEvent()
    data class OnCardNumberChanged(val cardNumber: String) : SearchEvent()
}