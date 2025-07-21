package com.leif2k.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.leif2k.cryptoapp.data.retrofit.ApiFactory
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinsResponse
import com.leif2k.cryptoapp.data.retrofit.pojo.DetailCoinResponse
import com.leif2k.cryptoapp.data.room.AppDatabase
import com.leif2k.cryptoapp.data.room.CoinFullInfo
import com.leif2k.cryptoapp.domain.CoinsRepository

class CoinsRepositoryImpl(
    private val application: Application
): CoinsRepository {

    private val retrofitApiService = ApiFactory.apiService
    private val roomDatabase = AppDatabase.getInstance(application).coinsDao()



    override suspend fun getTopCoinsInfo(limit: Int): CoinsResponse {
        return retrofitApiService.getTopCoinsInfo(limit)
    }

    override suspend fun getDetailCoinInfo(ticker: String): DetailCoinResponse {
        return retrofitApiService.getDetailCoinInfo(ticker)
    }



    override fun getCoinFullInfoList(): LiveData<List<CoinFullInfo>> {
        return roomDatabase.getCoinFullInfoList()
    }

    override fun getCoinFullInfoItem(ticker: String): LiveData<CoinFullInfo> {
        return roomDatabase.getCoinFullInfoItem(ticker)
    }

    override suspend fun insertCoinFullInfoList(list: List<CoinFullInfo>) {
        roomDatabase.insertCoinFullInfoList(list)
    }

    override suspend fun getCoinFullInfoListWithoutLivedata(): List<CoinFullInfo> {
        return roomDatabase.getCoinFullInfoListWithoutLivedata()
    }
}