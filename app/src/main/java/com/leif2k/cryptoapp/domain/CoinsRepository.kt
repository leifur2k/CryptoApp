package com.leif2k.cryptoapp.domain

import androidx.lifecycle.LiveData
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinsResponse
import com.leif2k.cryptoapp.data.retrofit.pojo.DetailCoinResponse
import com.leif2k.cryptoapp.data.room.CoinFullInfo

interface CoinsRepository {

    suspend fun getTopCoinsInfo(limit: Int): CoinsResponse

    suspend fun getDetailCoinInfo(ticker: String): DetailCoinResponse


    fun getCoinFullInfoList(): LiveData<List<CoinFullInfo>>

    fun getCoinFullInfoItem(ticker: String): LiveData<CoinFullInfo>

    suspend fun insertCoinFullInfoList(list: List<CoinFullInfo>)

    suspend fun getCoinFullInfoListWithoutLivedata(): List<CoinFullInfo>
}