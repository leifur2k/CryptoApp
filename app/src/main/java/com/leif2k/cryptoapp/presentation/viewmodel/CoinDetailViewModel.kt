package com.leif2k.cryptoapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.leif2k.cryptoapp.data.repositoryImpl.CoinsRepositoryImpl
import com.leif2k.cryptoapp.data.room.CoinFullInfo
import com.leif2k.cryptoapp.domain.GetCoinFullInfoItemUseCase

class CoinDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinsRepositoryImpl(application)
    private val getCoinFullInfoItemUseCase = GetCoinFullInfoItemUseCase(repository)

    fun getDetailInfo(ticker: String): LiveData<CoinFullInfo> {
        return getCoinFullInfoItemUseCase(ticker)
    }

}