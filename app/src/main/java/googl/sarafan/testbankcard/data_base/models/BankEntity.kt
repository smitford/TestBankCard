package googl.sarafan.testbankcard.data_base.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "banks",
    foreignKeys = [
        ForeignKey(
            entity = CountryEntity::class,
            parentColumns = ["countryId"],
            childColumns = ["countryId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class BankEntity(
    @PrimaryKey(autoGenerate = true)
    val bankId: Int = 0,
    val bankName: String,
    val url: String,
    val phone: String,
    val city: String,
    val countryId: Int
)