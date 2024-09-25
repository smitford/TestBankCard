package googl.sarafan.testbankcard.features.search.data

import android.util.Log
import googl.sarafan.testbankcard.features.search.data.models.SearchCardRequest
import googl.sarafan.testbankcard.network.api.SearchCardApi
import googl.sarafan.testbankcard.network.models.NetworkException
import javax.inject.Inject

class RetrofitNetworkClient @Inject constructor(private val api: SearchCardApi) {

    suspend fun doRequest(dto: Any): Result<Any?> {
        return when (dto) {
            is SearchCardRequest -> try {
                val response = api.searchCard(dto.cardNumber).body()
                Log.d("Card", "$response")
                return Result.success(response)
            } catch (e: Exception) {
                Log.d("Exception", "$e")
                return Result.failure(e)
            }

            else -> {
                Result.failure(exception = NetworkException.Undefined())
            }
        }
    }
}
