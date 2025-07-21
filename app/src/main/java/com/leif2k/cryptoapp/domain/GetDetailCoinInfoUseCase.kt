package com.leif2k.cryptoapp.domain

class GetDetailCoinInfoUseCase(
    private val repository: CoinsRepository
) {

    suspend operator fun invoke(ticker: String) = repository.getDetailCoinInfo(ticker)
}