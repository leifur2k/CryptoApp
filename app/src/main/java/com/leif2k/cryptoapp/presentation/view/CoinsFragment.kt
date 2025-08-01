package com.leif2k.cryptoapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.leif2k.cryptoapp.R
import com.leif2k.cryptoapp.databinding.FragmentCoinsBinding
import com.leif2k.cryptoapp.presentation.adapters.CoinsAdapter
import com.leif2k.cryptoapp.presentation.viewmodel.CoinsViewModel


class CoinsFragment : Fragment() {

    private var _binding: FragmentCoinsBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentCoinsBinding == null")


    private lateinit var viewModel: CoinsViewModel
    private lateinit var adapter: CoinsAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinsBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CoinsAdapter()
        binding.rvCoins.adapter = adapter
        binding.rvCoins.itemAnimator = null

        viewModel = ViewModelProvider(this)[CoinsViewModel::class.java]
        viewModel.getTopCoins()
        viewModel.coinFullInfoListFromRoom.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        adapter.onCoinClickListener = {
            launchCoinDetailFragment(it.ticker)
        }

        viewModel.errorNetwork.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Нет подключения к Интернет!", Toast.LENGTH_LONG).show()
        }

    }



    override fun onStart() {
        super.onStart()
        viewModel.updateAllCoins()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopUpdate()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    private fun launchCoinDetailFragment(ticker: String) {
        val fragment = CoinDetailFragment.newInstance(ticker)
        requireActivity().supportFragmentManager.beginTransaction()
            .hide(this)
            .add(R.id.mainFragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

}