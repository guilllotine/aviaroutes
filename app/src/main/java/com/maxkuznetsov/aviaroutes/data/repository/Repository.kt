package com.maxkuznetsov.aviaroutes.data.repository

import com.maxkuznetsov.aviaroutes.data.model.RouteCoordinates
import io.reactivex.Flowable

/**
 * Created by max
 */
interface Repository {

    fun getCoordinates(from: String, to: String, lang: String): Flowable<RouteCoordinates>

}