package com.leif2k.cryptoapp.data.retrofit.pojo

import com.google.gson.annotations.SerializedName

data class CoinsResponse(
    @SerializedName("Data")
    val coins: List<Coin>
)