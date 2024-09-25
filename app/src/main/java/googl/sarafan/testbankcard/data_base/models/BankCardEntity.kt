package googl.sarafan.testbankcard.data_base.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "bank_cards",
    foreignKeys = [
        ForeignKey(
            entity = BankEntity::class,
            parentColumns = ["bankId"],
            childColumns = ["bankId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CountryEntity::class,
            parentColumns = ["countryId"],
            childColumns = ["countryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BankCardEntity(
    @PrimaryKey(autoGenerate = true)
    val cardId: Int = 0,
    val cardNumber: String,
    val cardScheme: String,
    val cardType: String,
    val bankId: Int,
    val countryId: Int
)
