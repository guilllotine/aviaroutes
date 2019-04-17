package com.maxkuznetsov.aviaroutes.data.remote

import com.maxkuznetsov.aviaroutes.data.model.SearchResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by max
 */
interface CoordinatesService {

    @GET("autocomplete")
    fun getLocation(@Query("term") term: String, @Query("lng") lang: String): Flowable<SearchResponse>
}