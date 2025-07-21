package com.leif2k.cryptoapp.domain

class GetCoinFullInfoListUseCase(
    private val repository: CoinsRepository
) {

    operator fun invoke() = repository.getCoinFullInfoList()
}