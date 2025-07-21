package com.leif2k.cryptoapp.data.retrofit.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leif2k.cryptoapp.utils.convertTimestampToTime

@Entity(tableName = "full_price_list")
data class CoinFullInfo(

    @PrimaryKey
    val ticker: String,
    val fullName: String? = null,
    val imageUrl: String? = null,
    val toSymbol: String? = null,
    val lastUpdate: Long? = null,
    val price: String? = null,
    val market: String? = null,
    val lastMarket: String? = null,
    val topTierVolume24Hour: String? = null,
    val topTierVolume24HourTo: String? = null,
    val change24Hour: String? = null,
    val volume24HourToRaw: Double? = null
) {
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageIrl(): String {
        return "https://www.cryptocompare.com/$imageUrl"
    }
}