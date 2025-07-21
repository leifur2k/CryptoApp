package com.leif2k.cryptoapp.data.retrofit.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.leif2k.cryptoapp.utils.convertTimestampToTime

data class CurrencyInfo(

    @SerializedName("TYPE")
    val type: String? = null,

    @SerializedName("MARKET")
    val market: String? = null,

    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    val toSymbol: String? = null,

    @SerializedName("FLAGS")
    val flags: String? = null,

    @SerializedName("LASTMARKET")
    val lastMarket: String? = null,

    @SerializedName("MEDIAN")
    val median: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOUR")
    val topTierVolume24Hour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    val topTierVolume24HourTo: Double? = null,

    @SerializedName("LASTTRADEID")
    val lastTradeId: String? = null,

    @SerializedName("PRICE")
    val price: Double? = null,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Long? = null,

    @SerializedName("LASTVOLUME")
    val lastVolume: Double? = null,

    @SerializedName("LASTVOLUMETO")
    val lastVolumeTo: Double? = null,

    @SerializedName("VOLUMEHOUR")
    val volumeHour: Double? = null,

    @SerializedName("VOLUMEHOURTO")
    val volumeHourTo: Double? = null,

    @SerializedName("OPENHOUR")
    val openHour: Double? = null,

    @SerializedName("HIGHHOUR")
    val highHour: Double? = null,

    @SerializedName("LOWHOUR")
    val lowHour: Double? = null,

    @SerializedName("VOLUMEDAY")
    val volumeDay: Double? = null,

    @SerializedName("VOLUMEDAYTO")
    val volumeDayTo: Double? = null,

    @SerializedName("OPENDAY")
    val openDay: Double? = null,

    @SerializedName("HIGHDAY")
    val highDay: Double? = null,

    @SerializedName("LOWDAY")
    val lowDay: Double? = null,

    @SerializedName("VOLUME24HOUR")
    val volume24Hour: Double? = null,

    @SerializedName("VOLUME24HOURTO")
    val volume24HourTo: Double? = null,

    @SerializedName("OPEN24HOUR")
    val open24Hour: Double? = null,

    @SerializedName("HIGH24HOUR")
    val high24Hour: Double? = null,

    @SerializedName("LOW24HOUR")
    val low24Hour: Double? = null,

    @SerializedName("CHANGE24HOUR")
    val change24Hour: Double? = null,

    @SerializedName("CHANGEPCT24HOUR")
    val changePct24Hour: Double? = null,

    @SerializedName("CHANGEDAY")
    val changeDay: Double? = null,

    @SerializedName("CHANGEPCTDAY")
    val changePctDay: Double? = null,

    @SerializedName("CHANGEHOUR")
    val changeHour: Double? = null,

    @SerializedName("CHANGEPCTHOUR")
    val changePctHour: Double? = null,

    @SerializedName("CONVERSIONTYPE")
    val conversionType: String? = null,

    @SerializedName("CONVERSIONSYMBOL")
    val conversionSymbol: String? = null,

    @SerializedName("CONVERSIONLASTUPDATE")
    val conversionLastUpdate: Int? = null,

    @SerializedName("SUPPLY")
    val supply: Int? = null,

    @SerializedName("MKTCAP")
    val mktCap: Double? = null,

    @SerializedName("MKTCAPPENALTY")
    val mktCapPenalty: Int? = null,

    @SerializedName("CIRCULATINGSUPPLY")
    val circulatingSupply: Int? = null,

    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    val circulatingSuppLymktCap: Double? = null,

    @SerializedName("TOTALVOLUME24H")
    val totalVolume24h: Double? = null,

    @SerializedName("TOTALVOLUME24HTO")
    val totalVolume24hTo: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    val totalTopTierVolume24h: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    val totalTopTierVolume24hTo: Double? = null,

    @SerializedName("IMAGEURL")
    val imageUrl: String? = null
) {

    fun getFormattedTime(): String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageIrl(): String {
        if (imageUrl == null) return ""
        return "https://www.cryptocompare.com/$imageUrl"
    }
}