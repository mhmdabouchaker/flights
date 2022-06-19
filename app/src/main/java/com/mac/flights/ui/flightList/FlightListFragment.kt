package com.mac.flights.ui.flightList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mac.flights.R
import com.mac.flights.databinding.FragmentFlightListingBinding
import com.mac.flights.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FlightListFragment: Fragment() {

    private val viewModel: FlightListViewModel by viewModels()

    private lateinit var binding: FragmentFlightListingBinding
    private lateinit var flightsAdapter: FlightListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeFlights()
    }

    private fun setupAdapter() = with(binding){
        flightsAdapter = FlightListAdapter()
        flightListView.layoutManager = LinearLayoutManager(activity)
        flightListView.setHasFixedSize(true)
        flightListView.adapter = flightsAdapter

       flightsAdapter.setOnFlightClickListener { flightsData, currency ->
           val navAction =
              FlightListFragmentDirections.actionFlightListFragmentToFlightDetailsFragment(
                  currency, flightsData
              )

           findNavController().navigate(navAction)
       }
    }

    private fun observeFlights(){
        lifecycleScope.launch {
            viewModel.flightsList.collectLatest { result ->
                when(result?.status){
                    Result.Status.LOADING->{
                        binding.loadingView.visibility = View.VISIBLE
                    }
                    Result.Status.SUCCESS->{
                        result.data?.let { list ->
                            flightsAdapter.updateCurrency(list.currency)
                            flightsAdapter.submitList(list.data)
                        }
                        binding.loadingView.visibility = View.GONE
                    }
                    Result.Status.ERROR->{
                        binding.loadingView.visibility = View.GONE
                        result.message?.let {
                            showError(it)
                        }
                    }
                    else -> {
                        showError(getString(R.string.error_unknown))
                    }
                }

            }
        }
    }

    private fun showError(msg: String) {
        Snackbar.make(binding.parentRl, msg, Snackbar.LENGTH_INDEFINITE).setAction(getString(R.string.action_dismiss)) {
        }.show()
    }
}