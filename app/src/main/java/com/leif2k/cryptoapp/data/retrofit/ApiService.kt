package com.leif2k.cryptoapp.data.retrofit

import com.leif2k.cryptoapp.data.retrofit.pojo.CoinsResponse
import com.leif2k.cryptoapp.data.retrofit.pojo.DetailCoinResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val DEFAULT_LIMIT_VALUE = 10
        private const val DEFAULT_TO_SYMBOL_VALUE = "USD"

        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"

        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
    }


    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_LIMIT) limit: Int = DEFAULT_LIMIT_VALUE,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = DEFAULT_TO_SYMBOL_VALUE
    ): CoinsResponse


    @GET("pricemultifull")
    suspend fun getDetailCoinInfo(
        @Query(QUERY_PARAM_FROM_SYMBOLS) ticker: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = DEFAULT_TO_SYMBOL_VALUE
    ): DetailCoinResponse

}