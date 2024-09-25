package googl.sarafan.testbankcard.features.search.presentation.models

data class CardValue(
    val cardScheme: String = "",
    val cardType: String = "",
    val country: Country = Country(),
    val bank: Bank = Bank(),
)
