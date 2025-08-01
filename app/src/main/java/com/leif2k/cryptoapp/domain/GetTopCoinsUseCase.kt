package com.leif2k.cryptoapp.domain

class GetTopCoinsUseCase(
    private val repository: CoinsRepository
) {

    suspend operator fun invoke(limit: Int) = repository.getTopCoins(limit)
}