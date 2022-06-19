package com.mac.flights.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Availability(
    val seats: Int?
): Parcelable
