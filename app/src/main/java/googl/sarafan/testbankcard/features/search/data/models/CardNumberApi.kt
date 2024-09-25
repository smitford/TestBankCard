package googl.sarafan.testbankcard.features.search.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CardNumberApi(
    val length: Int? = null,
    val luhn: Boolean? = null
)
