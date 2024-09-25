package googl.sarafan.testbankcard.features.search.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CountryApi(
    val numeric: String? = null,
    val alpha2: String? = null,
    val name: String? = null,
    val emoji: String? = null,
    val currency: String? = null,
    val latitude: Float? = null,
    val longitude: Float? = null
)
