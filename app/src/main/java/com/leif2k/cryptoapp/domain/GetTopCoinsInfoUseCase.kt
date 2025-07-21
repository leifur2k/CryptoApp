package com.leif2k.cryptoapp.domain

class GetTopCoinsInfoUseCase(
    private val repository: CoinsRepository
) {

    suspend operator fun invoke(limit: Int) = repository.getTopCoinsInfo(limit)
}