package com.mac.flights.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightsResponse(
    val currency: String,
    val data: List<FlightsData>
): Parcelable
