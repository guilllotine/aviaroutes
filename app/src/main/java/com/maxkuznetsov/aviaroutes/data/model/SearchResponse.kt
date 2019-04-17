package com.maxkuznetsov.aviaroutes.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by max
 */
data class SearchResponse(
    @Expose
    @SerializedName("cities")
    val cities: List<City>
)