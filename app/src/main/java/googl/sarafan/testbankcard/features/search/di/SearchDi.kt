package googl.sarafan.testbankcard.features.search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import googl.sarafan.testbankcard.features.search.data.CardNetworkRepository
import googl.sarafan.testbankcard.features.search.domain.use_case.CallNumberUseCase
import googl.sarafan.testbankcard.features.search.domain.use_case.OpenLinkUseCase
import googl.sarafan.testbankcard.features.search.domain.use_case.OpenMapUseCase
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

    @Provides
    @Singleton
    fun provideCallNumberUseCase(
        callNumberUseCase: CallNumberUseCase.Base
    ): CallNumberUseCase {
        return callNumberUseCase
    }

    @Provides
    @Singleton
    fun provideOpenLinkUseCase(
        openLinkUseCase: OpenLinkUseCase.Base
    ): OpenLinkUseCase {
        return openLinkUseCase
    }

    @Provides
    @Singleton
    fun provideOpenMapUseCase(
        openMapUseCase: OpenMapUseCase.Base
    ): OpenMapUseCase {
        return openMapUseCase

    }
}