package com.leif2k.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leif2k.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var ivLogoCoin: ImageView
    private lateinit var tvSymbols: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvMinPrice: TextView
    private lateinit var tvMaxPrice: TextView
    private lateinit var tvLastMarket: TextView
    private lateinit var tvLastUpdate: TextView

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coin_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fSym = intent.getStringExtra(EXTRA_FROM_SYMBOL)

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fSym!!).observe(this, Observer {
            Picasso.get().load(it.getFullImageIrl()).into(ivLogoCoin)
            tvSymbols.text = it.fromSymbol + " / " + it.toSymbol
            tvPrice.text = "Цена: " + it.price.toString()
            tvMinPrice.text = "Минимум за день: ${it.lowDay}"
            tvMaxPrice.text = "Максимум за день: ${it.highDay}"
            tvLastMarket.text = "Последняя сделка: ${it.lastMarket}"
            tvLastUpdate.text = "Обновлено: " + convertTimestampToTime(it.lastUpdate)
        })


    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fSym: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fSym)
            return intent
        }
    }

    private fun initViews() {
        ivLogoCoin = findViewById(R.id.ivLogoCoin)
        tvSymbols = findViewById(R.id.tvSymbols)
        tvPrice = findViewById(R.id.tvPrice)
        tvMinPrice = findViewById(R.id.tvMinPrice)
        tvMaxPrice = findViewById(R.id.tvMaxPrice)
        tvLastMarket = findViewById(R.id.tvLastMarket)
        tvLastUpdate = findViewById(R.id.tvLastUpdate)
    }
}