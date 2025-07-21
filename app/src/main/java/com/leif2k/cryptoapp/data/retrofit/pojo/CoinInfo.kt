package com.leif2k.cryptoapp.data.retrofit.pojo

import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("Name")
    val ticker: String,

    @SerializedName("FullName")
    val fullName: String,

    @SerializedName("ImageUrl")
    val imageUrl: String
)