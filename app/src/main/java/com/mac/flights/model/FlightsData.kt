package com.mac.flights.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightsData(
    val id: String,
    val cityFrom: String,
    val cityTo: String,
    val countryFrom: CountryFrom,
    val countryTo: CountryTo,
    val dTime: Long,
    val mapIdto: String,
    val fly_duration: String,
    val price: Double,
    val availability: Availability,
    val technical_stops: Int
):Parcelable
