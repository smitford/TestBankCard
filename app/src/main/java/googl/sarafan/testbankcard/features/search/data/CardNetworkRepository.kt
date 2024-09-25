package googl.sarafan.testbankcard.features.search.data

import googl.sarafan.testbankcard.features.search.data.models.CardValueApi
import googl.sarafan.testbankcard.features.search.data.models.SearchCardRequest
import javax.inject.Inject

interface CardNetworkRepository {

    suspend fun searchCard(cardNumber: String): Result<CardValueApi>

    class Base @Inject constructor(private val client: RetrofitNetworkClient) :
        CardNetworkRepository {
        override suspend fun searchCard(cardNumber: String): Result<CardValueApi> {
            val response = client.doRequest(SearchCardRequest(cardNumber = cardNumber))
            return response.map {
                when (it) {
                    is CardValueApi -> return Result.success(it)
                    else -> return Result.failure(it as Exception)
                }
            }
        }
    }
}