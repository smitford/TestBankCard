package googl.sarafan.testbankcard.features.search.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CardValueApi(
    val number: CardNumberApi? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: CountryApi? = null,
    val bank: BankApi? = null
)
