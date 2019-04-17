package com.maxkuznetsov.aviaroutes.ui.base

import com.maxkuznetsov.aviaroutes.data.model.RouteCoordinates

/**
 * Created by max
 */
interface NavigationListener {

    fun navigateToRouteLoading(routeCoordinates: RouteCoordinates)
}