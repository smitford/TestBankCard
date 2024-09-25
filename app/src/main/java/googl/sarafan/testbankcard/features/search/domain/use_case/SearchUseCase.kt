package googl.sarafan.testbankcard.features.search.domain.use_case

import googl.sarafan.testbankcard.features.search.SearchMapper
import googl.sarafan.testbankcard.features.search.data.CardNetworkRepository
import googl.sarafan.testbankcard.features.search.presentation.models.CardValue
import javax.inject.Inject

interface SearchUseCase {

    suspend fun invoke(cardNumber: String): Result<CardValue>

    class Base @Inject constructor(
        private val repository: CardNetworkRepository,
        private val mapper: SearchMapper
    ) : SearchUseCase {
        override suspend fun invoke(cardNumber: String): Result<CardValue> {
            val result = repository.searchCard(cardNumber = cardNumber)
            return result.map { successResult ->
                mapper.fromApi(successResult)
            }
        }

    }
}