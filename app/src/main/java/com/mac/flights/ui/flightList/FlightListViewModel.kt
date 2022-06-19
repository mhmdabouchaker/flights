package com.mac.flights.ui.flightList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mac.flights.data.FlightsRepository
import com.mac.flights.model.FlightsResponse
import com.mac.flights.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for FlightListFragment
 */
@HiltViewModel
class FlightListViewModel @Inject constructor(private val flightsRepository: FlightsRepository) :
    ViewModel(){

    private val _flightsList = MutableStateFlow<Result<FlightsResponse>?>(Result.loading())
    val flightsList = _flightsList

    init {
        getFlights()
    }
    private fun getFlights(){
        viewModelScope.launch {
            flightsRepository.getFlights().collectLatest {
                _flightsList.value = it
            }
        }
    }

}