package com.leif2k.cryptoapp.data.repositoryImpl

import android.app.Application
import androidx.lifecycle.LiveData
import com.leif2k.cryptoapp.data.mapper.CoinsMapper
import com.leif2k.cryptoapp.data.retrofit.ApiFactory
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinInfo
import com.leif2k.cryptoapp.data.room.AppDatabase
import com.leif2k.cryptoapp.data.room.CoinFullInfo
import com.leif2k.cryptoapp.domain.CoinsRepository

class CoinsRepositoryImpl(
    private val application: Application
): CoinsRepository {

    private val retrofitApiService = ApiFactory.apiService
    private val roomDatabase = AppDatabase.getInstance(application).coinsDao()
    private val mapper = CoinsMapper()



    override fun getCoinFullInfoList(): LiveData<List<CoinFullInfo>> {
        return roomDatabase.getCoinFullInfoList()
    }

    override fun getCoinFullInfoItem(ticker: String): LiveData<CoinFullInfo> {
        return roomDatabase.getCoinFullInfoItem(ticker)
    }



    override suspend fun getTopCoins(limit: Int) {
        val coins = retrofitApiService.getTopCoinsInfo(limit).coins.map { it.coinInfo }
        loadData(coins)
    }

    override suspend fun updateAllCoins() {
        val roomCoins = roomDatabase.getCoinFullInfoListWithoutLivedata()
        val coins = roomCoins.map { CoinInfo(it.ticker, it.fullName, it.imageUrl) }
        loadData(coins)
    }



    private suspend fun loadData(coins: List<CoinInfo>) {
        val tickers = coins.joinToString(",") { it.ticker }
        val detailCoinResponse = retrofitApiService.getDetailCoinInfo(tickers)
        val coinFullInfoList = mapper.createCoinFullInfoList(coins, detailCoinResponse)
        roomDatabase.insertCoinFullInfoList(coinFullInfoList)
    }




}