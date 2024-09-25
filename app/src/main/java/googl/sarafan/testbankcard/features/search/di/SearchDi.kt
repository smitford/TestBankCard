package googl.sarafan.testbankcard.features.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import googl.sarafan.testbankcard.features.search.data.CardNetworkRepository
import googl.sarafan.testbankcard.features.search.domain.use_case.SaveCardSearchUseCase
import googl.sarafan.testbankcard.features.search.domain.use_case.SearchUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchDi {
    @Provides
    @Singleton
    fun provideSearchUseCase(
        searchUseCase: SearchUseCase.Base
    ): SearchUseCase {
        return searchUseCase
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(
        repository: CardNetworkRepository.Base
    ): CardNetworkRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideSaveCardSearchUseCase(
        saveCardSearchUseCase: SaveCardSearchUseCase.Base
    ): SaveCardSearchUseCase {
        return saveCardSearchUseCase
    }

}