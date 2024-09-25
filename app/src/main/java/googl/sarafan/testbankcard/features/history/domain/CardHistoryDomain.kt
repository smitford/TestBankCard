package googl.sarafan.testbankcard.features.history.domain

data class CardHistoryDomain(
    val cardNumber: String,
    val cardScheme: String,
    val cardType: String,
    val countryName: String,
    val latitude: Float,
    val longitude: Float,
    val bankName: String,
    val url: String,
    val phone: String,
    val city: String
)
