package com.leif2k.cryptoapp.domain

import com.leif2k.cryptoapp.data.room.CoinFullInfo

class InsertCoinFullInfoListUseCase(
    private val repository: CoinsRepository
) {

    suspend operator fun invoke(list: List<CoinFullInfo>) = repository.insertCoinFullInfoList(list)
}