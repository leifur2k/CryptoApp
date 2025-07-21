package com.leif2k.cryptoapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinFullInfo
import com.leif2k.cryptoapp.data.retrofit.pojo.CurrencyInfo
import com.leif2k.cryptoapp.data.room.AppDatabase

class CoinDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val roomDataBase = AppDatabase.getInstance(application).coinsDao()

    fun getDetailInfo(ticker: String): LiveData<CoinFullInfo> {
        return roomDataBase.getCoinFullInfoItem(ticker)
    }

}