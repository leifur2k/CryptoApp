package com.leif2k.cryptoapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leif2k.cryptoapp.data.room.CoinFullInfo

@Dao
interface CoinsDao {

    @Query("SELECT * FROM full_price_list ORDER BY volume24HourToRaw DESC")
    fun getCoinFullInfoList(): LiveData<List<CoinFullInfo>>

    @Query("SELECT * FROM full_price_list WHERE ticker == :ticker LIMIT 1")
    fun getCoinFullInfoItem(ticker: String): LiveData<CoinFullInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinFullInfoList(list: List<CoinFullInfo>)

    @Query("SELECT * FROM full_price_list ORDER BY volume24HourToRaw DESC")
    suspend fun getCoinFullInfoListWithoutLivedata(): List<CoinFullInfo>
}
