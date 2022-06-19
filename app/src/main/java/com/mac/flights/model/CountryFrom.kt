package com.mac.flights.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryFrom(
    val name: String
): Parcelable