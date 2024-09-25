package googl.sarafan.testbankcard.data_base.models

data class BankCardDt(
    val cardNumber: String,
    val cardScheme: String,
    val cardType: String,
    val bankName: String,
    val bankUrl: String,
    val bankPhone: String,
    val bankCity: String,
    val countryName: String,
    val latitude: Float,
    val longitude: Float
)
