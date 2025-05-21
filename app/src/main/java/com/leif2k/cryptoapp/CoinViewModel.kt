package com.leif2k.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.leif2k.cryptoapp.pojo.CoinsDetailResponse
import com.leif2k.cryptoapp.pojo.CurrencyInfo
import com.leif2k.cryptoapp.retrofit.ApiFactory
import com.leif2k.cryptoapp.room.AppDatabase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    private val myLog = "myLog"

    val priceList = db.coinsDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym: String): LiveData<CurrencyInfo> {
        return db.coinsDao().getPriceInfoAboutCoin(fSym)
    }

    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
            .map { it.coins?.map { it.coinInfo?.name }?.joinToString(",").toString() }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it)!! }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(myLog, it.toString())
                db.coinsDao().insertPriceList(it)
            }, {
                    Log.d(myLog, it.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(response: CoinsDetailResponse): List<CurrencyInfo>? {
        val list = mutableListOf<CurrencyInfo>()

        val jsonObject = response.coinJsonObject ?: return null
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CurrencyInfo::class.java
                )
                list.add(priceInfo)
            }
        }
        return list
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

