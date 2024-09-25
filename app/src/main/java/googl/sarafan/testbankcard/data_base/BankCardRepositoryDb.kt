package googl.sarafan.testbankcard.data_base

import googl.sarafan.testbankcard.data_base.models.BankCardDt
import googl.sarafan.testbankcard.data_base.dao.BankCardDao
import googl.sarafan.testbankcard.data_base.models.BankCardEntity
import googl.sarafan.testbankcard.data_base.models.BankEntity
import googl.sarafan.testbankcard.data_base.models.CountryEntity
import googl.sarafan.testbankcard.features.history.HistoryMapper
import googl.sarafan.testbankcard.features.history.domain.CardHistoryDomain
import javax.inject.Inject

interface BankCardRepositoryDb {

    suspend fun saveBankCard(card: BankCardDt)

    suspend fun getAllBankCards(): List<CardHistoryDomain>

    suspend fun deleteAllBankCards()

    class Base @Inject constructor(
        private val dao: BankCardDao,
        private val mapper: HistoryMapper
    ) : BankCardRepositoryDb {
        override suspend fun saveBankCard(card: BankCardDt) {

            var countryDao = dao.getCountryByName(countryName = card.countryName)

            if (countryDao == null) {
                val newCountry = CountryEntity(
                    countryName = card.countryName,
                    latitude = card.latitude,
                    longitude = card.longitude
                )
                dao.insertCountry(newCountry)
                countryDao = dao.getCountryByName(card.countryName)
            }

            var bankDao = dao.getBankByName(card.bankName)

            if (bankDao == null) {
                val newBank = BankEntity(
                    bankName = card.bankName,
                    url = card.bankUrl,
                    phone = card.bankPhone,
                    city = card.bankCity,
                    countryId = countryDao!!.countryId
                )
                dao.insertBank(newBank)
                bankDao = dao.getBankByName(card.bankName)
            }

            val newBankCard = BankCardEntity(
                cardNumber = card.cardNumber,
                cardScheme = card.cardScheme,
                cardType = card.cardType,
                bankId = bankDao!!.bankId,
                countryId = countryDao!!.countryId
            )

            dao.insertCard(newBankCard)
        }

        override suspend fun getAllBankCards(): List<CardHistoryDomain> {
            val allCards = dao.getAllCreditCardsWithDetails() ?: return emptyList()
            return allCards.map { bankCard -> mapper.fromDaoToUi(bankCard) }
        }

        override suspend fun deleteAllBankCards() {
            dao.deleteBankCard()
            dao.deleteBank()
            dao.deleteCountry()
        }

    }
}