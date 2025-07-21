package com.leif2k.cryptoapp.data.retrofit.pojo

import com.google.gson.annotations.SerializedName

data class CoinDisplayData(
    @SerializedName("PRICE")
    val price: String,

    @SerializedName("MARKET")
    val market: String,

    @SerializedName("LASTMARKET")
    val lastMarket: String,


    @SerializedName("TOPTIERVOLUME24HOUR")
    val topTierVolume24Hour: String,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    val topTierVolume24HourTo: String,



    @SerializedName("CHANGE24HOUR")
    val change24Hour: String
)
