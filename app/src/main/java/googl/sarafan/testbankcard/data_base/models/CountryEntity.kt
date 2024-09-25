package googl.sarafan.testbankcard.data_base.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val countryId: Int = 0,
    val countryName: String,
    val latitude: Float,
    val longitude: Float
)
