package googl.sarafan.testbankcard.data_base.models

import androidx.room.Embedded
import androidx.room.Relation

data class BankCardWithBankAndCountry(
    @Embedded val creditCard: BankCardEntity,
    @Relation(
        parentColumn = "bankId",
        entityColumn = "bankId"
    )
    val bank: BankEntity,
    @Relation(
        parentColumn = "countryId",
        entityColumn = "countryId"
    )
    val country: CountryEntity
)
