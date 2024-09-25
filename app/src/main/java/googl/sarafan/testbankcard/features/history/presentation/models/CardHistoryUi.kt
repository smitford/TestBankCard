package googl.sarafan.testbankcard.features.history.presentation.models

data class CardHistoryUi(
    val cardNumber: String = "",
    val cardScheme: String = "",
    val cardType: String = "",
    val bankName: String = "",
    val url: String = "",
    val phone: String = "",
    val city: String = "",
    val countryName: String = "",
    val latitude: Float = 0f,
    val longitude: Float = 0f
)
