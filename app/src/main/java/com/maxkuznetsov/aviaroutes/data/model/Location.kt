package com.maxkuznetsov.aviaroutes.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by max
 */
@Parcelize
data class Location(
    @Expose
    @SerializedName("lat")
    val lat: Double,

    @Expose
    @SerializedName("lon")
    val lng: Double
): Parcelable