package com.leif2k.cryptoapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leif2k.cryptoapp.data.repositoryImpl.CoinsRepositoryImpl
import com.leif2k.cryptoapp.domain.GetCoinFullInfoListUseCase
import com.leif2k.cryptoapp.domain.GetTopCoinsUseCase
import com.leif2k.cryptoapp.domain.UpdateAllCoinsUseCase
import com.leif2k.cryptoapp.common.log
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CoinsViewModel(application: Application) : AndroidViewModel(application) {

    private var job: Job? = null

    private val repository = CoinsRepositoryImpl(application)

    private val getCoinFullInfoListUseCase = GetCoinFullInfoListUseCase(repository)
    private val getTopCoinsUseCase = GetTopCoinsUseCase(repository)
    private val updateAllCoinsUseCase = UpdateAllCoinsUseCase(repository)



    val coinFullInfoListFromRoom = getCoinFullInfoListUseCase()

    private val _errorNetwork = MutableLiveData<Boolean>()
    val errorNetwork: LiveData<Boolean>
        get() = _errorNetwork




    fun getTopCoins() {
        viewModelScope.launch {
            try {
                getTopCoinsUseCase(50)
                log("CoinsViewModel", "getTopCoins() Done")
            } catch (e: Exception) {
                _errorNetwork.value = true
            }
        }
    }


    fun updateAllCoins() {
        job = viewModelScope.launch {
            while (isActive) {
                delay(11000)
                try {
                    updateAllCoinsUseCase()
                    log("CoinsViewModel", "updateAllCoins() Done")
                } catch (e: Exception) {
                    _errorNetwork.value = true
                }
            }
        }
    }


    fun stopUpdate() {
        job?.cancel()
    }

}