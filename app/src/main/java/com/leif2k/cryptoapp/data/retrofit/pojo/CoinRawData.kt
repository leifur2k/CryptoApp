package com.leif2k.cryptoapp.data.retrofit.pojo

import com.google.gson.annotations.SerializedName

data class CoinRawData(
    @SerializedName("TOSYMBOL")
    val toSymbol: String,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Long,

    @SerializedName("VOLUME24HOURTO")
    val volume24HourToRaw: Double
)
