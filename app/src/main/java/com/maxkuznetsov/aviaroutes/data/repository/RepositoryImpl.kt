package com.maxkuznetsov.aviaroutes.data.repository

import com.maxkuznetsov.aviaroutes.data.model.RouteCoordinates
import com.maxkuznetsov.aviaroutes.data.remote.CoordinatesService
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

/**
 * Created by max
 */
class RepositoryImpl(private val service: CoordinatesService) : Repository {

    override fun getCoordinates(from: String, to: String, lang: String): Flowable<RouteCoordinates> {
        return Flowable.zip(
            service.getLocation(from, lang).map { it.cities.first().location },
            service.getLocation(to, lang).map { it.cities.first().location },
            BiFunction { fromLocation, toLocation -> RouteCoordinates(fromLocation, toLocation) })
    }

}