package com.leif2k.cryptoapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinsResponse(
    @SerializedName("Data")
    @Expose
    val coins: List<Coin>? = null
)