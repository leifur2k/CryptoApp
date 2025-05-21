package com.leif2k.cryptoapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null
)