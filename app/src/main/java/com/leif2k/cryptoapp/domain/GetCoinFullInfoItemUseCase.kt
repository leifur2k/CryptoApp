package com.leif2k.cryptoapp.domain

class GetCoinFullInfoItemUseCase(
    private val repository: CoinsRepository
) {

    operator fun invoke(ticker: String) = repository.getCoinFullInfoItem(ticker)
}