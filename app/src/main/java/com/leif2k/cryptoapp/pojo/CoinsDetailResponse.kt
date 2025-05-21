package com.leif2k.cryptoapp.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinsDetailResponse(
    @SerializedName("RAW")
    @Expose
    val coinJsonObject: JsonObject? = null
)