package googl.sarafan.testbankcard.network.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import googl.sarafan.testbankcard.network.ErrorInterceptor
import googl.sarafan.testbankcard.network.NetworkConnectionProvider
import googl.sarafan.testbankcard.network.NetworkConnectionProviderImpl
import googl.sarafan.testbankcard.network.api.SearchCardApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClientBuilder

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClient

@Module
@InstallIn(SingletonComponent::class)
class NetworkDi {

    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideNetworkConnectionProvider(@ApplicationContext context: Context): NetworkConnectionProvider {
        return NetworkConnectionProviderImpl(context)
    }

    @Singleton
    @OkhttpClientBuilder
    @Provides
    fun provideOkhttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @OkhttpClient
    @Provides
    fun provideOkHttpClient(
        errorInterceptor: ErrorInterceptor,
        @OkhttpClientBuilder okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpClientBuilder
            .addInterceptor(errorInterceptor)
            .build()
    }


    @Provides
    @RetrofitApi
    fun provideRetrofit(
        @OkhttpClient okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder().baseUrl("https://lookup.binlist.net/").client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
    }

    @Provides
    @Singleton
    fun provideSearchCardApi(
       @RetrofitApi retrofit: Retrofit
    ): SearchCardApi {
        return retrofit.create(SearchCardApi::class.java)
    }

}