package com.leif2k.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.leif2k.cryptoapp.data.room.CoinFullInfo

class CoinDiffCallback : DiffUtil.ItemCallback<CoinFullInfo>() {

    override fun areItemsTheSame(
        oldItem: CoinFullInfo,
        newItem: CoinFullInfo
    ): Boolean {
        return oldItem.ticker == newItem.ticker
    }

    override fun areContentsTheSame(
        oldItem: CoinFullInfo,
        newItem: CoinFullInfo
    ): Boolean {
        return oldItem == newItem
    }
}