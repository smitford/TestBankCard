package googl.sarafan.testbankcard.features.history.domain

import googl.sarafan.testbankcard.data_base.BankCardRepositoryDb
import googl.sarafan.testbankcard.features.history.HistoryMapper
import googl.sarafan.testbankcard.features.history.presentation.models.CardHistoryUi
import javax.inject.Inject

interface GetHistoryUseCase {
    suspend fun invoke(): List<CardHistoryUi>
    class Base @Inject constructor(
        private val repository: BankCardRepositoryDb,
        private val mapper: HistoryMapper
    ) :
        GetHistoryUseCase {
        override suspend fun invoke(): List<CardHistoryUi> {
            val result = repository.getAllBankCards()
            return result.map { card -> mapper.fromDomainToUi(card) }
        }
    }
}