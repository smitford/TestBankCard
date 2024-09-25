package googl.sarafan.testbankcard.features.search.presentation.models

data class SearchUiState(
    val cardNumber: String = "",
    val isLoading: Boolean = false,
    val cardValue: CardValue = CardValue(),
)
