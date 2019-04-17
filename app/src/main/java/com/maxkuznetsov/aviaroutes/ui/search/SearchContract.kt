package com.maxkuznetsov.aviaroutes.ui.search

import com.maxkuznetsov.aviaroutes.data.model.RouteCoordinates
import com.maxkuznetsov.aviaroutes.ui.base.presenter.BasePresenter
import com.maxkuznetsov.aviaroutes.ui.base.view.LoadingView

/**
 * Created by max
 */
interface SearchContract {

    interface View: LoadingView {
        fun showRouteLoadingScreen(routeCoordinates: RouteCoordinates)
        fun showError()
        fun enableSearch()
        fun disableSearch()
    }

    interface Presenter: BasePresenter<View> {
        fun loadRoute()
        fun changeFrom(from: String)
        fun changeTo(to: String)
    }

}