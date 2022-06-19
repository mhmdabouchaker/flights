package com.mac.flights.model

data class FlightsResponse(
    val currency: String,
    val data: List<FlightsData>
)
