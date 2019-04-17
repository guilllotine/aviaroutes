package com.maxkuznetsov.aviaroutes.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by max
 */
@Parcelize
data class RouteCoordinates(
    val fromLocation: Location,
    val toLocation: Location
) : Parcelable