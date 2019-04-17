package com.maxkuznetsov.aviaroutes.ui.search


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maxkuznetsov.aviaroutes.AppDelegate
import com.maxkuznetsov.aviaroutes.R
import com.maxkuznetsov.aviaroutes.data.model.RouteCoordinates
import com.maxkuznetsov.aviaroutes.ui.base.NavigationListener
import com.maxkuznetsov.aviaroutes.ui.base.fragment.BaseFragment
import com.maxkuznetsov.aviaroutes.ui.search.di.SearchFragmentModule
import com.maxkuznetsov.aviaroutes.ui.widget.SimpleTextWatcher
import kotlinx.android.synthetic.main.fragment_search.refresher
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_search.from_point as fromPoint
import kotlinx.android.synthetic.main.fragment_search.search_button as searchButton
import kotlinx.android.synthetic.main.fragment_search.to_point as toPoint

/**
 * Created by max
 */
class SearchFragment : BaseFragment(), SearchContract.View {

    @Inject
    lateinit var presenter: SearchContract.Presenter

    private lateinit var navigationListener: NavigationListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDelegate.app.getAppComponent().plus(SearchFragmentModule()).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButton.setOnClickListener { presenter.loadRoute() }
        refresher.isEnabled = false
        fromPoint.addTextChangedListener(object : SimpleTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let { presenter.changeFrom(it.toString()) }
            }
        })
        toPoint.addTextChangedListener(object : SimpleTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let { presenter.changeTo(it.toString()) }
            }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        navigationListener = context as? NavigationListener
                ?: throw IllegalStateException("activity must implement " + NavigationListener::class.java.name)
    }

    override fun bindView() {
        presenter.bindView(this)
    }

    override fun unbindView() {
        presenter.unbindView(this)
    }

    override fun showRouteLoadingScreen(routeCoordinates: RouteCoordinates) {
        navigationListener.navigateToRouteLoading(routeCoordinates)
    }

    override fun showError() {
        Snackbar.make(refresher, R.string.error_common, Snackbar.LENGTH_LONG).show()
    }

    override fun showLoadingIndicator() {
        refresher.isRefreshing = true
    }

    override fun hideLoadingIndicator() {
        refresher.isRefreshing = false
    }

    override fun enableSearch() {
        searchButton.isEnabled = true
    }

    override fun disableSearch() {
        searchButton.isEnabled = false
    }

    companion object {
        fun create() = SearchFragment()
    }
}