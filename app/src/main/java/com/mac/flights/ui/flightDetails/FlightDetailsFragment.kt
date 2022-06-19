package com.mac.flights.ui.flightDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mac.flights.R
import com.mac.flights.databinding.FragmentFlightDetailsBinding
import kotlinx.android.synthetic.main.fragment_flight_listing.*
import java.text.SimpleDateFormat
import java.util.*

class FlightDetailsFragment : Fragment() {

    val args: FlightDetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentFlightDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightDetailsBinding.inflate(inflater, parent, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
    }

    private fun updateUi()= with(binding){
        val flight = args.flight
        tvCountryName.text = flight.countryTo.name
        val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        tvFlightDate.text = String.format("%s: %s", getString(R.string.flight_date), date)
        tvFlightDuration.text = String.format("%s: %s", getString(R.string.flight_duration), flight.fly_duration)
        tvSeatsAvailable.text = String.format("%s: %s", getString(R.string.seats_left), flight.availability.seats ?: "0")
        tvFlightPrice.text = String.format("%s: %s%s", getString(R.string.price), args.currency, flight.price)
    }
}