package googl.sarafan.testbankcard.features.history.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import googl.sarafan.testbankcard.features.history.HistoryMapper
import googl.sarafan.testbankcard.features.history.domain.DeleteHistoryUseCase
import googl.sarafan.testbankcard.features.history.domain.GetHistoryUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HistoryDi {
    @Provides
    @Singleton
    fun provideMapper(): HistoryMapper {
        return HistoryMapper()
    }

    @Provides
    @Singleton
    fun provideDeleteHistoryUseCase(
        deleteHistoryUseCase: DeleteHistoryUseCase.Base
    ): DeleteHistoryUseCase {
        return deleteHistoryUseCase
    }

    @Provides
    @Singleton
    fun provideHistoryUseCase(
        getHistoryUseCase: GetHistoryUseCase.Base
    ): GetHistoryUseCase {
        return getHistoryUseCase
    }

}