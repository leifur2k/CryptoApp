package com.leif2k.cryptoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leif2k.cryptoapp.R
import com.leif2k.cryptoapp.pojo.CurrencyInfo
import com.leif2k.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CurrencyInfo> = arrayListOf<CurrencyInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]

        with(holder) {
            tvSymbols.text = coin.fromSymbol + " / " + coin.toSymbol
            tvPrice.text = coin.price.toString()
            tvLastUpdate.text =
                "Время последнего обновления: " + convertTimestampToTime(coin.lastUpdate)
        }

        Picasso.get().load(coin.getFullImageIrl()).into(holder.ivLogoCoin)

        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }

    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    interface OnCoinClickListener {
        fun onCoinClick(currencyInfo: CurrencyInfo)
    }

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin = itemView.findViewById<ImageView>(R.id.ivLogoCoin)
        val tvSymbols = itemView.findViewById<TextView>(R.id.tvSymbols)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvLastUpdate = itemView.findViewById<TextView>(R.id.tvLastUpdate)
    }
}