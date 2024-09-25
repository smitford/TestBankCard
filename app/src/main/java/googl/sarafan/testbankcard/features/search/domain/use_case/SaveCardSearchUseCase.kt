package googl.sarafan.testbankcard.features.search.domain.use_case

import googl.sarafan.testbankcard.data_base.BankCardRepositoryDb
import googl.sarafan.testbankcard.features.search.SearchMapper
import googl.sarafan.testbankcard.features.search.presentation.models.CardValue
import javax.inject.Inject

interface SaveCardSearchUseCase {
    suspend fun invoke(cardNumber: String, cardValue: CardValue)

    class Base @Inject constructor(
        private val repositoryDb: BankCardRepositoryDb,
        private val mapper: SearchMapper
    ) : SaveCardSearchUseCase {
        override suspend fun invoke(cardNumber: String, cardValue: CardValue) {
            repositoryDb.saveBankCard(
                mapper.fromUiToDomain(
                    cardValue = cardValue,
                    cardNumber = cardNumber
                )
            )
        }
    }
}