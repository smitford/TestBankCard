package googl.sarafan.testbankcard.data_base.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import googl.sarafan.testbankcard.data_base.models.BankCardEntity
import googl.sarafan.testbankcard.data_base.models.BankEntity
import googl.sarafan.testbankcard.data_base.models.CountryEntity

@Database(
    entities = [
        BankCardEntity::class,
        BankEntity::class,
        CountryEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun cardDao(): BankCardDao
}