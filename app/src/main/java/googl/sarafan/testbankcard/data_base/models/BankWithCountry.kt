package googl.sarafan.testbankcard.data_base.models

import androidx.room.Embedded
import androidx.room.Relation
import googl.sarafan.testbankcard.features.search.presentation.models.Bank
import googl.sarafan.testbankcard.features.search.presentation.models.Country

data class BankWithCountry(
    @Embedded val bank: Bank,
    @Relation(
        parentColumn = "countryId",
        entityColumn = "countryId"
    )
    val country: Country
)
