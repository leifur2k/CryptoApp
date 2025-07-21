package com.leif2k.cryptoapp.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.leif2k.cryptoapp.R
import com.leif2k.cryptoapp.data.retrofit.pojo.CoinFullInfo
import com.leif2k.cryptoapp.databinding.ItemCoinInfoBinding
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
                context.getString(R.string.tv_last_update, coin.getFormattedTime())
        }

        Picasso.get().load(coin.getFullImageIrl()).into(binding.ivLogoCoin)

        binding.root.setOnClickListener {
            onCoinClickListener?.invoke(coin)
        }
    }

}