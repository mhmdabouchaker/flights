package com.mac.flights.model

data class FlightsData(
    val id: String,
    val cityFrom: String,
    val cityTo: String,
    val countryFrom: CountryFrom,
    val countryTo: CountryTo,
    val dTimeUTC: Int,
    val aTimeUTC: Int,
    val mapIdto: String,
    val fly_duration: String,
    val price: Double,
    val availability: Availability,
    val technical_stops: Int
)
