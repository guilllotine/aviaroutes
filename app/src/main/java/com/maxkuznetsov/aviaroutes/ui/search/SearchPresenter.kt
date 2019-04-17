package com.maxkuznetsov.aviaroutes.ui.search

import com.maxkuznetsov.aviaroutes.data.model.RouteCoordinates
import com.maxkuznetsov.aviaroutes.data.repository.Repository
import com.maxkuznetsov.aviaroutes.ui.base.presenter.BasePresenterImpl
import com.maxkuznetsov.aviaroutes.util.async
import com.maxkuznetsov.aviaroutes.util.loading

/**
 * Created by max
 */
class SearchPresenter(
    private val repository: Repository,
    private val lang: String
) : BasePresenterImpl<SearchContract.View>(),
    SearchContract.Presenter {

    private var from: String = ""
    private var to: String = ""

    override fun loadRoute() {
        val disposable = repository.getCoordinates(from, to, lang)
            .async()
            .loading(view())
            .subscribe({ onSuccess(it) }, { onError() })
        disposeOnUnbindView(disposable)
    }

    override fun changeFrom(from: String) {
        this.from = from
        validateInput()
    }

    override fun changeTo(to: String) {
        this.to = to
        validateInput()
    }

    private fun onSuccess(routeCoordinates: RouteCoordinates) {
        view()?.showRouteLoadingScreen(routeCoordinates)
    }

    private fun onError() {
        view()?.showError()
    }

    private fun validateInput() {
        var isValid = true
        if (from.isEmpty()) {
            isValid = false
        }
        if (to.isEmpty()) {
            isValid = false
        }
        if (isValid) {
            view()?.enableSearch()
        } else {
            view()?.disableSearch()
        }
    }

}