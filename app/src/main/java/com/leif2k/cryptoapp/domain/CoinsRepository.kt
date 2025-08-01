package com.leif2k.cryptoapp.domain

import androidx.lifecycle.LiveData
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinsResponse
import com.leif2k.cryptoapp.data.retrofit.pojo.DetailCoinResponse
import com.leif2k.cryptoapp.data.room.CoinFullInfo

interface CoinsRepository {

    fun getCoinFullInfoList(): LiveData<List<CoinFullInfo>>

    fun getCoinFullInfoItem(ticker: String): LiveData<CoinFullInfo>


    suspend fun getTopCoins(limit: Int)

    suspend fun updateAllCoins()
}