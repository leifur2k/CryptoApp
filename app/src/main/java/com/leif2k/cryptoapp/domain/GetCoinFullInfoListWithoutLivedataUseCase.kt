package com.leif2k.cryptoapp.domain

class GetCoinFullInfoListWithoutLivedataUseCase(
    private val repository: CoinsRepository
) {

    suspend operator fun invoke() = repository.getCoinFullInfoListWithoutLivedata()
}