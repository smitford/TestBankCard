package googl.sarafan.testbankcard.features.search

import googl.sarafan.testbankcard.data_base.models.BankCardDt
import googl.sarafan.testbankcard.features.search.data.models.BankApi
import googl.sarafan.testbankcard.features.search.data.models.CardValueApi
import googl.sarafan.testbankcard.features.search.data.models.CountryApi
import googl.sarafan.testbankcard.features.search.presentation.models.Bank
import googl.sarafan.testbankcard.features.search.presentation.models.CardValue
import googl.sarafan.testbankcard.features.search.presentation.models.Country
import javax.inject.Inject


class SearchMapper @Inject constructor() {
    fun fromApi(cardValueApi: CardValueApi): CardValue {
        return CardValue(
            cardScheme = cardValueApi.scheme ?: "-",
            cardType = cardValueApi.type ?: "-",
            country = fromApi(cardValueApi.country),
            bank = fromApi(cardValueApi.bank),
        )
    }

    private fun fromApi(bankApi: BankApi?): Bank {
        return if (bankApi != null) {
            Bank(
                bankName = bankApi.name ?: "-",
                url = bankApi.url ?: "-",
                phone = bankApi.phone ?: "-",
                city = bankApi.city ?: "-"
            )
        } else {
            Bank()
        }
    }

    private fun fromApi(countryApi: CountryApi?): Country {
        return if (countryApi != null) {
            Country(
                countryName = countryApi.name ?: "-",
                latitude = countryApi.latitude ?: 0f,
                longitude = countryApi.longitude ?: 0f
            )
        } else {
            Country()
        }
    }

    fun fromUiToDomain(cardValue: CardValue, cardNumber: String): BankCardDt {
        return BankCardDt(
            cardNumber = cardNumber,
            cardScheme = cardValue.cardScheme,
            cardType = cardValue.cardType,
            bankName = cardValue.bank.bankName,
            bankUrl = cardValue.bank.url,
            bankPhone = cardValue.bank.phone,
            bankCity = cardValue.bank.city,
            countryName = cardValue.country.countryName,
            latitude = cardValue.country.latitude,
            longitude = cardValue.country.longitude
        )
    }

}