package com.leif2k.cryptoapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.leif2k.cryptoapp.data.repository.CoinsRepositoryImpl
import com.leif2k.cryptoapp.data.retrofit.ApiFactory
import com.leif2k.cryptoapp.data.retrofit.pojo.Coin
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinDisplayData
import com.leif2k.cryptoapp.data.room.CoinFullInfo
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinInfo
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinRawData
import com.leif2k.cryptoapp.data.retrofit.pojo.DetailCoinResponse
import com.leif2k.cryptoapp.data.room.AppDatabase
import com.leif2k.cryptoapp.domain.GetCoinFullInfoItemUseCase
import com.leif2k.cryptoapp.domain.GetCoinFullInfoListUseCase
import com.leif2k.cryptoapp.domain.GetCoinFullInfoListWithoutLivedataUseCase
import com.leif2k.cryptoapp.domain.GetDetailCoinInfoUseCase
import com.leif2k.cryptoapp.domain.GetTopCoinsInfoUseCase
import com.leif2k.cryptoapp.domain.InsertCoinFullInfoListUseCase
import com.leif2k.cryptoapp.utils.convertTimestampToTime
import com.leif2k.cryptoapp.utils.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CoinsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinsRepositoryImpl(application)

    private val getTopCoinsInfoUseCase = GetTopCoinsInfoUseCase(repository)
    private val getDetailCoinInfoUseCase = GetDetailCoinInfoUseCase(repository)

    private val insertCoinFullInfoListUseCase = InsertCoinFullInfoListUseCase(repository)
    private val getCoinFullInfoListWithoutLivedataUseCase = GetCoinFullInfoListWithoutLivedataUseCase(repository)
    private val getCoinFullInfoListUseCase = GetCoinFullInfoListUseCase(repository)



    private var job: Job? = null



    val coinFullInfoListFromRoom = getCoinFullInfoListUseCase()



    fun loadData() {
        viewModelScope.launch {
            try {
                val coins = getTopCoinsInfoUseCase.invoke(50).coins
                work(coins)
            } catch (e: Exception) {

            }
            log("CoinsViewModel", "loadData() Done")
        }
    }


    fun loadData2() {
        job = viewModelScope.launch {
            while (isActive) {
                delay(11000)
                val roomCoinList = getCoinFullInfoListWithoutLivedataUseCase.invoke()
                val coinList = roomCoinListToCoinList(roomCoinList)
                work(coinList)
                log("CoinsViewModel", "loadData2() Done")
            }
        }
        log("CoinsViewModel", "loadData2() Close")
    }

    private fun roomCoinListToCoinList(roomCoinList: List<CoinFullInfo>): List<Coin> {
        val coinList = mutableListOf<Coin>()

        for (coin in roomCoinList) {
            val coinInfo = CoinInfo(coin.ticker, coin.fullName.toString(), coin.imageUrl.toString())
            val coin = Coin(coinInfo)
            coinList.add(coin)
        }

        return coinList
    }


    private suspend fun work(coins: List<Coin>) {
        val detailCoinResponse = getDetailCoinResponse(coins)
        val coinFullInfoList = createCoinFullInfoList(coins, detailCoinResponse)
        insertCoinFullInfoListUseCase(coinFullInfoList)
    }

    private suspend fun getDetailCoinResponse(coins: List<Coin>): DetailCoinResponse {
        var tickers = StringBuilder()

        for (coin in coins) {
            tickers.append("${coin.coinInfo.ticker},")
        }
        tickers.deleteCharAt(tickers.lastIndex)

        val response = getDetailCoinInfoUseCase(tickers.toString())
        return response
    }

    private fun createCoinFullInfoList(
        coins: List<Coin>,
        detailCoinResponse: DetailCoinResponse
    ): List<CoinFullInfo> {

        val result = mutableListOf<CoinFullInfo>()

        for (coin in coins) {
            val coinRawData = detailCoinResponse.raw[coin.coinInfo.ticker]?.values?.first()
            val coinDisplayData = detailCoinResponse.display[coin.coinInfo.ticker]?.values?.first()

            if (coinRawData != null && coinDisplayData != null) {
                val coinFullInfo = createCoinFullInfo(coin.coinInfo, coinRawData, coinDisplayData)
                result.add(coinFullInfo)
            }
        }

        return result
    }

    private fun createCoinFullInfo(
        coinInfo: CoinInfo,
        coinRawData: CoinRawData,
        coinDisplayData: CoinDisplayData
    ): CoinFullInfo {
        return CoinFullInfo(
            ticker = coinInfo.ticker,
            fullName = coinInfo.fullName,
            imageUrl = coinInfo.imageUrl,
            toSymbol = coinRawData.toSymbol,
            lastUpdate = coinRawData.lastUpdate,
            price = coinDisplayData.price,
            market = coinDisplayData.market,
            lastMarket = coinDisplayData.lastMarket,
            topTierVolume24Hour = coinDisplayData.topTierVolume24Hour,
            topTierVolume24HourTo = coinDisplayData.topTierVolume24HourTo,
            change24Hour = coinDisplayData.change24Hour,
            volume24HourToRaw = coinRawData.volume24HourToRaw
        )
    }


    fun stopLoadData2() {
        job?.cancel()
    }
}