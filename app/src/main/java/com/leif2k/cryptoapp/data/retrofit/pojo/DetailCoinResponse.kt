package com.leif2k.cryptoapp.data.retrofit.pojo

import com.google.gson.annotations.SerializedName

data class DetailCoinResponse(
    @SerializedName("RAW")
    val raw: Map<String, Map<String, CoinRawData>>,

    @SerializedName("DISPLAY")
    val display: Map<String, Map<String, CoinDisplayData>>
)