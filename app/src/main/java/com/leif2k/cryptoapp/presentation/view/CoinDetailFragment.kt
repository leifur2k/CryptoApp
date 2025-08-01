package com.leif2k.cryptoapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.leif2k.cryptoapp.data.room.CoinFullInfo
import com.leif2k.cryptoapp.databinding.FragmentCoinDetailBinding
import com.leif2k.cryptoapp.presentation.viewmodel.CoinDetailViewModel
import com.leif2k.cryptoapp.common.IMAGE_URL_HEADER
import com.leif2k.cryptoapp.common.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding == null")

    private lateinit var viewModel: CoinDetailViewModel

    private lateinit var ticker: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CoinDetailViewModel::class.java]
        viewModel.getDetailInfo(ticker).observe(viewLifecycleOwner) {
            setViews(it)
        }

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.ivArrowBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setViews(coinFullInfo: CoinFullInfo) {
        with(binding) {

            val imageUrl = IMAGE_URL_HEADER + coinFullInfo.imageUrl
            Picasso.get().load(imageUrl).into(ivLogoCoin)

            tvCoinFullName.text = coinFullInfo.fullName
            tvSymbols.text = "(${coinFullInfo.ticker} / ${coinFullInfo.toSymbol})"
            tvPrice.text = coinFullInfo.price.toString()
            tvVolume24Hour.text = "Объем за 24 часа: ${coinFullInfo.topTierVolume24Hour}"
            tvVolume24HourTo.text = "Объем за 24 часа: ${coinFullInfo.topTierVolume24HourTo}"
            tvLastMarket.text = "Последняя сделка: ${coinFullInfo.lastMarket}"
            tvMarket.text = "Маркет: ${coinFullInfo.market}"
            tvChange24Hour.text = "Изменение за 24 часа: ${coinFullInfo.change24Hour}"

            tvLastUpdate.text = "Обновлено: " + convertTimestampToTime(coinFullInfo.lastUpdate)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun parseArgs() {
        val args = requireArguments()
        ticker = args.getString(EXTRA_FROM_SYMBOL, "")
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newInstance(ticker: String): CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, ticker)
                }
            }
        }

    }

}