package com.leif2k.cryptoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.leif2k.cryptoapp.R
import com.leif2k.cryptoapp.data.room.CoinFullInfo
import com.leif2k.cryptoapp.databinding.ItemCoinInfoBinding
import com.leif2k.cryptoapp.common.IMAGE_URL_HEADER
import com.leif2k.cryptoapp.common.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinsAdapter : ListAdapter<CoinFullInfo, CoinViewHolder>(CoinDiffCallback()) {

    var onCoinClickListener: ((CoinFullInfo) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding =
            ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        val binding = holder.binding
        val context = holder.itemView.context

        with(binding) {
            tvCoinFullName.text = coin.fullName
            tvSymbols.text = context.getString(R.string.tv_symbols, coin.ticker, coin.toSymbol)
            tvPrice.text = coin.price.toString()
            tvLastUpdate.text =
                context.getString(R.string.tv_last_update, convertTimestampToTime(coin.lastUpdate))
        }

        val imageUrl = IMAGE_URL_HEADER + coin.imageUrl
        Picasso.get().load(imageUrl).into(binding.ivLogoCoin)

        binding.root.setOnClickListener {
            onCoinClickListener?.invoke(coin)
        }
    }

}