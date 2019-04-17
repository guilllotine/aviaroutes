package com.maxkuznetsov.aviaroutes.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by max
 */
data class City(
    @Expose
    @SerializedName("id")
    val id: Long,

    @Expose
    @SerializedName("countryCode")
    val countryCode: String,

    @Expose
    @SerializedName("latinFullName")
    val latinFullName: String,

    @Expose
    @SerializedName("fullname")
    val fullName: String,

    @Expose
    @SerializedName("location")
    val location: Location,

    @Expose
    @SerializedName("iata")
    val iata: List<String>,

    @Expose
    @SerializedName("city")
    val city: String,

    @Expose
    @SerializedName("latinCity")
    val latinCity: String
)