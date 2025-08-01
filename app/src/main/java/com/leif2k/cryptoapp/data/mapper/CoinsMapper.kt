package com.leif2k.cryptoapp.data.mapper

import com.leif2k.cryptoapp.data.retrofit.pojo.CoinDisplayData
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinInfo
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinRawData
import com.leif2k.cryptoapp.data.retrofit.pojo.DetailCoinResponse
import com.leif2k.cryptoapp.data.room.CoinFullInfo

class CoinsMapper {


    fun createCoinFullInfoList(
        coins: List<CoinInfo>,
        detailCoinResponse: DetailCoinResponse
    ): List<CoinFullInfo> {

        val result = mutableListOf<CoinFullInfo>()

        for (coin in coins) {
            val coinRawData = detailCoinResponse.raw[coin.ticker]?.values?.first()
            val coinDisplayData = detailCoinResponse.display[coin.ticker]?.values?.first()

            if (coinRawData != null && coinDisplayData != null) {
                val coinFullInfo = createCoinFullInfo(coin, coinRawData, coinDisplayData)
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


}