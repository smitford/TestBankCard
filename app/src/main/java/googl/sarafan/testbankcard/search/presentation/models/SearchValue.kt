package googl.sarafan.testbankcard.search.presentation.models

data class SearchValue(
    val cardScheme: String = "",
    val cardType: String = "",
    val country: Country = Country(),
    val bank: Bank = Bank(),
)
