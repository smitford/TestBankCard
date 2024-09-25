package googl.sarafan.testbankcard.features.search.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BankApi(
    val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)
