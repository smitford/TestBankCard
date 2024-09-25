package googl.sarafan.testbankcard.features.history

import googl.sarafan.testbankcard.data_base.models.BankCardWithBankAndCountry
import googl.sarafan.testbankcard.features.history.domain.CardHistoryDomain
import googl.sarafan.testbankcard.features.history.presentation.models.CardHistoryUi

class HistoryMapper {
    fun fromDomainToUi(bankCard: CardHistoryDomain): CardHistoryUi {
        return CardHistoryUi(
            cardNumber = bankCard.cardNumber,
            cardScheme = bankCard.cardScheme,
            cardType = bankCard.cardType,
            bankName = bankCard.bankName,
            url = bankCard.url,
            phone = bankCard.phone,
            city = bankCard.city,
            countryName = bankCard.countryName,
            latitude = bankCard.latitude,
            longitude = bankCard.longitude
        )
    }

    fun fromDaoToUi(card: BankCardWithBankAndCountry): CardHistoryDomain {
        return CardHistoryDomain(
            cardNumber = card.creditCard.cardNumber,
            cardScheme = card.creditCard.cardScheme,
            cardType = card.creditCard.cardType,
            countryName = card.country.countryName,
            latitude = card.country.latitude,
            longitude = card.country.longitude,
            bankName = card.bank.bankName,
            url = card.bank.url,
            phone = card.bank.phone,
            city = card.bank.city
        )
    }
}