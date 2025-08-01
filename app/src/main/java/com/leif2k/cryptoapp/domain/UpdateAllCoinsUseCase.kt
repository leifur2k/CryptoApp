package com.leif2k.cryptoapp.domain

class UpdateAllCoinsUseCase(
    private val repository: CoinsRepository
) {

    suspend operator fun invoke() = repository.updateAllCoins()
}