package googl.sarafan.testbankcard.search.presentation.models

data class SearchState(
    val cardNumber: String = "",
    val isLoading: Boolean = false,
    val searchValue: SearchValue = SearchValue(),
)
