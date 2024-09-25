package googl.sarafan.testbankcard.network.api

import googl.sarafan.testbankcard.features.search.data.models.CardValueApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchCardApi {
    @GET("{cardNumber}")
    suspend fun searchCard(@Path("cardNumber") cardNumber: String): Response<CardValueApi>
}