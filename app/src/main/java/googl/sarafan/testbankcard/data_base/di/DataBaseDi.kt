package googl.sarafan.testbankcard.data_base.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import googl.sarafan.testbankcard.data_base.BankCardRepositoryDb
import googl.sarafan.testbankcard.data_base.dao.AppDataBase
import googl.sarafan.testbankcard.data_base.dao.BankCardDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseDi {
    @Provides
    @Singleton
    fun provideBankCardRepository(
        bankCardRepositoryDb: BankCardRepositoryDb.Base
    ): BankCardRepositoryDb {
        return bankCardRepositoryDb

    }

    @Provides
    @Singleton
    fun providesDataBase(
        @ApplicationContext appContext: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "bank_card_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBankCardDao(
        appDataBase: AppDataBase
    ): BankCardDao {
        return appDataBase.cardDao()

    }
}