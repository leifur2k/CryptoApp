package com.leif2k.cryptoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.leif2k.cryptoapp.adapters.CoinInfoAdapter
import com.leif2k.cryptoapp.pojo.CurrencyInfo


class CoinsActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter: CoinInfoAdapter
    private lateinit var rvCoinPriceList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coins)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        adapter = CoinInfoAdapter()
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(currencyInfo: CurrencyInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinsActivity, currencyInfo.fromSymbol)
                startActivity(intent)
            }
        }
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })


    }

    private fun initViews() {
        rvCoinPriceList = findViewById(R.id.rvCoinPriceList)
    }
}