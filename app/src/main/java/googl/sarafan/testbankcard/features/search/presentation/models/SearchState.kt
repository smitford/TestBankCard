package googl.sarafan.testbankcard.features.search.presentation.models

data class SearchState(
    val isLoading: Boolean = false,
    val cardValue: CardValue = CardValue(),
)
