package com.leif2k.cryptoapp.data.retrofit.pojo

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo
)