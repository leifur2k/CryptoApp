package com.leif2k.cryptoapp.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class CoinFullInfo(
    @PrimaryKey
    val ticker: String,
    val fullName: String,
    val imageUrl: String,
    val toSymbol: String,
    val lastUpdate: Long,
    val price: String,
    val market: String,
    val lastMarket: String,
    val topTierVolume24Hour: String,
    val topTierVolume24HourTo: String,
    val change24Hour: String,
    val volume24HourToRaw: Double
)