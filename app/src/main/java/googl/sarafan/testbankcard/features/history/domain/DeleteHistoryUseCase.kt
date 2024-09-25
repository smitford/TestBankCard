package googl.sarafan.testbankcard.features.history.domain

import googl.sarafan.testbankcard.data_base.BankCardRepositoryDb
import javax.inject.Inject

interface DeleteHistoryUseCase {
    suspend fun invoke()
    class Base @Inject constructor(private val repository: BankCardRepositoryDb) :
        DeleteHistoryUseCase {
        override suspend fun invoke() {
            repository.deleteAllBankCards()
        }

    }
}