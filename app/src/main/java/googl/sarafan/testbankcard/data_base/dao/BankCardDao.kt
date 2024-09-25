package googl.sarafan.testbankcard.data_base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import googl.sarafan.testbankcard.data_base.models.BankCardEntity
import googl.sarafan.testbankcard.data_base.models.BankCardWithBankAndCountry
import googl.sarafan.testbankcard.data_base.models.BankEntity
import googl.sarafan.testbankcard.data_base.models.CountryEntity

@Dao
interface BankCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(card: BankCardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBank(bank: BankEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(country: CountryEntity)

    @Transaction
    @Query("SELECT * FROM bank_cards")
    suspend fun getAllCreditCardsWithDetails(): List<BankCardWithBankAndCountry>?

    @Query("SELECT * FROM countries WHERE countryName = :countryName")
    suspend fun getCountryByName(countryName: String): CountryEntity?

    @Query("SELECT * FROM banks WHERE bankName = :bankName")
    suspend fun getBankByName(bankName: String): BankEntity?

    @Query("DELETE FROM bank_cards")
    suspend fun deleteBankCard()

    @Query("DELETE FROM banks")
    suspend fun deleteBank()

    @Query("DELETE FROM countries")
    suspend fun deleteCountry()

}